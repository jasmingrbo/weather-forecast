package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.WeatherRepository
import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.interactors.overview.exceptions.FetchWeatherException

class FetchWeather(private val repository: WeatherRepository) {
    suspend operator fun invoke(coordinates: Coordinates) = try {
        repository.getWeather(coordinates.latitude, coordinates.longitude)
    } catch (e: Exception) {
        throw FetchWeatherException(coordinates, e)
    }
}