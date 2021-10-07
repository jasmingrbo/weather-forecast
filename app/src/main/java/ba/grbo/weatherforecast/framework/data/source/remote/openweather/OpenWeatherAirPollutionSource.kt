package ba.grbo.weatherforecast.framework.data.source.remote.openweather

import ba.grbo.core.data.AirPollutionSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OpenWeatherAirPollutionSource @Inject constructor(
    private val openWeatherService: OpenWeatherService,
    private val dispatcher: CoroutineDispatcher,
) : AirPollutionSource {
    override suspend fun get(
        latitude: String,
        longitude: String,
    ) = withContext(dispatcher) {
        openWeatherService.getNetworkAirPollution(
            latitude = latitude,
            longitude = longitude
        ).toDomain()
    }
}