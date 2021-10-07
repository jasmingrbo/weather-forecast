package ba.grbo.weatherforecast.framework.mics

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.net.InetSocketAddress
import java.util.*

class NetworkManager(
    context: Context,
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher,
) {
    // Using SharedFlow instead of StateFlow, because SharedFlow waits for all collectors to
    // consume emitted value before emitting new values.
    private val _internetStatus = MutableSharedFlow<Boolean>(replay = 1)
    val internetStatus = _internetStatus.stateIn(scope, WhileSubscribed(5000), true)

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private lateinit var networkRequest: NetworkRequest

    private val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    private val validNetworks: MutableMap<Network, Boolean> =
        Collections.synchronizedMap(mutableMapOf())

    private lateinit var pinger: Job

    init {
        collectSubscriptionCount()
    }

    private fun collectSubscriptionCount() {
        _internetStatus.subscriptionCount
            .map { count -> count > 0 }
            .distinctUntilChanged()
            .onEach { isActive -> if (isActive) onActive() else  onInactive() }
            .onCompletion { onInactive() }
            .flowOn(dispatcher)
            .launchIn(scope)
    }

    private fun onActive() {
        scope.emitInternetStatus()
        if (!::networkCallback.isInitialized) networkCallback = createNetworkCallback()
        if (!::networkRequest.isInitialized) networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        cm.registerNetworkCallback(networkRequest, networkCallback)
        pinger = scope.keepPinging()
    }

    private fun onInactive() {
        if (::networkCallback.isInitialized) {
            pinger.cancel()
            cm.unregisterNetworkCallback(networkCallback)
            validNetworks.clear()
        }
    }

    // An improvement would be to either make delay duration longer for metered networks or
    // not to ping with metered networks at all.
    private fun CoroutineScope.keepPinging() = launch(dispatcher) {
        while (isActive) this@NetworkManager.emitInternetStatusWithDelay(5000)
    }

    // Note, onAvailable is not called when cellular is turned on if the wifi was already on,
    // it is called immediately after wifi is turned off and its onLost is done, so we get
    // no internet status for a brief moment. Smooth transition implemented by introducing
    // a small delay before consuming values when collecting the stream.
    private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            val (hasInternet, isNonMetered) = network.hasInternetAndIsNonMetered
            if (hasInternet) {
                validNetworks[network] = isNonMetered
                scope.emitInternetStatus()
            }
        }

        override fun onLost(network: Network) {
            validNetworks.remove(network)
            scope.emitInternetStatus()
        }
    }

    private fun CoroutineScope.emitInternetStatus() = launch(dispatcher) {
        this@NetworkManager.emitInternetStatus()
    }

    private suspend fun emitInternetStatusWithDelay(duration: Long) {
        delay(duration)
        emitInternetStatus()
    }

    private suspend fun emitInternetStatus() = emitInternetStatus(
        when {
            validNetworks.isEmpty() -> false
            validNetworks.containsValue(true) -> {
                val nonMetered = validNetworks.filter { it.value }
                val metered: Map<Network, Boolean> by lazy { validNetworks.filter { !it.value } }
                doNetworksHaveInternet(nonMetered) || doNetworksHaveInternet(metered)
            }
            else -> doNetworksHaveInternet(validNetworks)
        }
    )

    private suspend fun emitInternetStatus(hasInternet: Boolean) = coroutineScope {
        _internetStatus.emit(hasInternet)
    }

    private fun doesNetworkHaveInternet(network: Network) = try {
        val socket = network.socketFactory.createSocket()
        socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
        socket.close()
        true
    } catch (e: Exception) {
        false
    }

    private fun doNetworksHaveInternet(networks: Map<Network, Boolean>): Boolean {
        networks.forEach { (network, _) -> if (doesNetworkHaveInternet(network)) return true }
        return false
    }

    private val Network.hasInternetAndIsNonMetered: Pair<Boolean, Boolean>
        get() {
            val nc = cm.getNetworkCapabilities(this)
            return Pair(
                nc?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false,
                nc?.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED) ?: false
            )
        }
}