package ba.grbo.weatherforecast.framework.data

import android.util.Log
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnDoneImeActionPressed
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnEnabledChanged
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnFocusChanged
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnOverflowButtonClick
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnQueryChange
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnResetButtonClick
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnSoftwareKeyboardHidden
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnUpButtonClick
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToLong

@OptIn(ExperimentalCoroutinesApi::class)
class CommonBodyEventHandler @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
) {
    private var resetQueryJob: Job? = null

    operator fun invoke(event: CommonBodyEvent, state: CommonBodyState) = channelFlow {
        when (event) {
            is OnQueryChange -> onQueryChange(event.query, state)
            is OnFocusChanged -> onFocusChanged(event.focused, state)
            is OnEnabledChanged -> onEnabledChanged(event.enabled, state)
            is OnUpButtonClick -> onUpButtonClick(event.body, state)
            is OnResetButtonClick -> onResetButtonClick(state)
            is OnOverflowButtonClick -> {
            }
            is OnDoneImeActionPressed -> onDoneImeActionPressed(state)
            is OnSoftwareKeyboardHidden -> onSoftwareKeyboardHidden(state)
        }
    }.flowOn(dispatcher)

    private suspend fun ProducerScope<CommonBodyState>.onQueryChange(
        query: String,
        state: CommonBodyState,
    ) {
        resetQueryJob?.cancel()
        send(state.updateQuery(query))
    }

    private suspend fun ProducerScope<CommonBodyState>.onFocusChanged(
        focused: Boolean,
        state: CommonBodyState,
    ) = coroutineScope {
        if (focused) {
            resetQueryJob?.cancel()
            send(state.updateFocusedToTrue())
        } else {
            val unfocused = state.updateFocusedAndUnfocusToFalse()
            send(unfocused)
            resetQueryJob = launch { resetQuery(unfocused) }
        }
    }

    private suspend fun ProducerScope<CommonBodyState>.onEnabledChanged(
        enabled: Boolean,
        state: CommonBodyState,
    ) {
        send(state.updateEnabled(enabled))
    }

    private suspend fun ProducerScope<CommonBodyState>.onUpButtonClick(
        body: Body,
        state: CommonBodyState,
    ) {
        when (body) {
            Body.OVERVIEW -> onOverviewUpButtonClick(state)
            Body.DETAILS -> {
            }
            Body.SETTINGS -> {
            }
        }
    }

    private suspend fun ProducerScope<CommonBodyState>.onResetButtonClick(
        state: CommonBodyState,
    ) = coroutineScope {
        resetQueryJob = launch { resetQuery(state) }
    }

    private suspend fun ProducerScope<CommonBodyState>.onDoneImeActionPressed(
        state: CommonBodyState,
    ) {
        send(state.updateHideKeyboard(true))
    }

    private suspend fun ProducerScope<CommonBodyState>.onSoftwareKeyboardHidden(
        state: CommonBodyState,
    ) {
        send(state.updateHideKeyboard(false))
    }

    private suspend fun ProducerScope<CommonBodyState>.onOverviewUpButtonClick(
        state: CommonBodyState,
    ) {
        send(state.updateUnfocusToTrue()) // clears focus and triggers onFocusChanged
    }

    private suspend fun ProducerScope<CommonBodyState>.resetQuery(
        state: CommonBodyState,
        duration: Double = 150.0,
    ) {
        val query = (state.appBarState as CommonBodyState.AppBarState.Overview).value.query
        if (query.isEmpty()) return

        val delayDuration = (duration / query.length).roundToLong()

        suspend fun updateQuery(query: String) {
            if (query.isEmpty()) {
                Log.i("MojViewModel", "resetQuery DONE")
                send(state.updateQuery(query))
            } else {
                val temp = query.substring(0 until query.lastIndex)
                send(state.updateQuery(temp))
                delay(delayDuration)
                // Don't have to check for coroutineContext.isActive, because we are calling
                // delay, which is a library function, and all library functions are cancellable
                // by default, that is they check whether or not a job is in active state
                updateQuery(temp)
            }
        }

        updateQuery(query)
    }
}