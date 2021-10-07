package ba.grbo.weatherforecast.framework.di

import android.content.Context
import ba.grbo.weatherforecast.framework.mics.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.ActivityRetainedLifecycle
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

@InstallIn(ActivityRetainedComponent::class)
@Module
object NetworkManagerProvider {
    @ActivityRetainedScoped
    @Provides
    fun provideCoroutineScope(lifecycle: ActivityRetainedLifecycle) =  MainScope().also {
        lifecycle.addOnClearedListener { it.cancel() }
    }

    @ActivityRetainedScoped
    @Provides
    fun provideNetworkManager(
        @ApplicationContext context: Context,
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher
    ) = NetworkManager(context, scope, dispatcher)

    @ActivityRetainedScoped
    @Provides
    fun provideInternetStatus(networkManager: NetworkManager) = networkManager.internetStatus
}