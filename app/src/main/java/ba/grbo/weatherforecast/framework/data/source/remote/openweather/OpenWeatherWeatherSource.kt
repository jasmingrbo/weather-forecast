package ba.grbo.weatherforecast.framework.data.source.remote.openweather

import ba.grbo.core.data.WeatherSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class
OpenWeatherWeatherSource @Inject constructor(
    private val openWeatherService: OpenWeatherService,
    private val dispatcher: CoroutineDispatcher,
) : WeatherSource {
    override suspend fun get(
        latitude: String,
        longitude: String,
    ) = withContext(dispatcher) {
        openWeatherService.getNetworkWeather(
            latitude = latitude,
            longitude = longitude
        ).toDomain()
    }
}