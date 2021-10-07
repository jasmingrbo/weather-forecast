package ba.grbo.core.data.repositories

import ba.grbo.core.domain.dm.Weather

interface WeatherRepository {
    suspend fun getWeather(latitude: String, longitude: String): Weather
}