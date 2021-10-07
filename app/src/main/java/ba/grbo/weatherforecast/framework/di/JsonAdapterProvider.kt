package ba.grbo.weatherforecast.framework.di

import ba.grbo.core.domain.dm.AirPollution
import ba.grbo.core.domain.dm.Alert
import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.domain.dm.CurrentWeather
import ba.grbo.core.domain.dm.DailyForecast
import ba.grbo.core.domain.dm.HourlyForecast
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Qualifier

@InstallIn(ActivityRetainedComponent::class)
@Module
object JsonAdapterProvider {
    @ActivityRetainedScoped
    @CoordinatesJsonAdapter
    @Provides
    fun provideCoordinatesJsonAdapter(
        @BasicMoshi moshi: Moshi
    ): JsonAdapter<Coordinates> = moshi.adapter(Coordinates::class.java)

    @ActivityRetainedScoped
    @AirPollutionJsonAdapter
    @Provides
    fun provideAirPollutionJsonAdapter(
        @BasicMoshi moshi: Moshi
    ): JsonAdapter<AirPollution> = moshi.adapter(AirPollution::class.java)


    @ActivityRetainedScoped
    @CurrentWeatherJsonAdapter
    @Provides
    fun provideCurrentWeatherJsonAdapter(
        @BasicMoshi moshi: Moshi
    ): JsonAdapter<CurrentWeather> = moshi.adapter(CurrentWeather::class.java)


    @ActivityRetainedScoped
    @HourlyForecastsJsonAdapter
    @Provides
    fun provideHourlyForecastJsonAdapter(
        @BasicMoshi moshi: Moshi
    ): JsonAdapter<List<HourlyForecast>> = moshi.adapter(
        Types.newParameterizedType(List::class.java, HourlyForecast::class.java)
    )


    @ActivityRetainedScoped
    @DailyForecastsJsonAdapter
    @Provides
    fun provideDailyForecastJsonAdapter(
        @BasicMoshi moshi: Moshi
    ): JsonAdapter<List<DailyForecast>> = moshi.adapter(
        Types.newParameterizedType(List::class.java, DailyForecast::class.java)
    )


    @ActivityRetainedScoped
    @AlertsJsonAdapter
    @Provides
    fun provideAlertsJsonAdapter(
        @BasicMoshi moshi: Moshi
    ): JsonAdapter<Alert> = moshi.adapter(Alert::class.java)

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoordinatesJsonAdapter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AirPollutionJsonAdapter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CurrentWeatherJsonAdapter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HourlyForecastsJsonAdapter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DailyForecastsJsonAdapter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AlertsJsonAdapter