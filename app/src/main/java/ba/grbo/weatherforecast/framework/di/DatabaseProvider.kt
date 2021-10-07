package ba.grbo.weatherforecast.framework.di

import android.content.Context
import ba.grbo.weatherforecast.framework.data.source.local.WeatherForecastDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
object DatabaseProvider {
    @ActivityRetainedScoped
    @Provides
    fun provideWeatherCheckerDatabase(
        @ApplicationContext context: Context
    ): WeatherForecastDatabase = WeatherForecastDatabase.getInstance(context)
}