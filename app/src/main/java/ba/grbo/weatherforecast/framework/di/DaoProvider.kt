package ba.grbo.weatherforecast.framework.di

import ba.grbo.weatherforecast.framework.data.source.local.WeatherForecastDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
object DaoProvider {
    @ActivityRetainedScoped
    @Provides
    fun providePlaceDao(database: WeatherForecastDatabase) = database.placeDao

    @ActivityRetainedScoped
    @Provides
    fun provideQueryDao(database: WeatherForecastDatabase) = database.queryDao
}