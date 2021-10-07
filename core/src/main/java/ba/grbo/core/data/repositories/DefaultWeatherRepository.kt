package ba.grbo.core.data.repositories

import ba.grbo.core.data.WeatherSource

class DefaultWeatherRepository(private val source: WeatherSource): WeatherRepository {
    override suspend fun getWeather(
        latitude: String,
        longitude: String,
    ) = source.get(latitude, longitude)
}