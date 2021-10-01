package ba.grbo.weatherforecast.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutineDispatcherProvider {
    @Singleton
    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}