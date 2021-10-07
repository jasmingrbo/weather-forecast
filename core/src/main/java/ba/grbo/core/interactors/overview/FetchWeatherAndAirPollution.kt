package ba.grbo.core.interactors.overview

import ba.grbo.core.domain.dm.Coordinates
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class FetchWeatherAndAirPollution(
    private val fetchWeather: FetchWeather,
    private val fetchAirPollution: FetchAirPollution
) {
    suspend operator fun invoke(coordinates: Coordinates) = coroutineScope {
        Pair(
            async { fetchWeather(coordinates) },
            async { fetchAirPollution(coordinates) }
        )
    }
}