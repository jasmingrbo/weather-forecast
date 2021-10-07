package ba.grbo.weatherforecast.framework.di

import ba.grbo.core.data.AirPollutionSource
import ba.grbo.core.data.LocationSource
import ba.grbo.core.data.PlaceSource
import ba.grbo.core.data.QuerySource
import ba.grbo.core.data.WeatherSource
import ba.grbo.weatherforecast.framework.data.source.local.RoomPlaceSource
import ba.grbo.weatherforecast.framework.data.source.local.RoomQuerySource
import ba.grbo.weatherforecast.framework.data.source.remote.locationiq.LocationIQLocationSource
import ba.grbo.weatherforecast.framework.data.source.remote.openweather.OpenWeatherAirPollutionSource
import ba.grbo.weatherforecast.framework.data.source.remote.openweather.OpenWeatherWeatherSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Suppress("UNUSED")
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class SourceProvider {
    @ActivityRetainedScoped
    @Binds
    abstract fun bindRoomPlaceSource(placeSource: RoomPlaceSource): PlaceSource

    @ActivityRetainedScoped
    @Binds
    abstract fun bindRoomQuerySource(querySource: RoomQuerySource): QuerySource

    @ActivityRetainedScoped
    @Binds
    abstract fun bindLocationIQLocationSource(locationSource: LocationIQLocationSource): LocationSource

    @ActivityRetainedScoped
    @Binds
    abstract fun bindOpenWeatherWeatherSource(weatherSource: OpenWeatherWeatherSource): WeatherSource

    @ActivityRetainedScoped
    @Binds
    abstract fun bindOpenWeatherAirPollutionSource(airPollutionSource: OpenWeatherAirPollutionSource): AirPollutionSource
}