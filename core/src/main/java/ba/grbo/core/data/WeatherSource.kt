package ba.grbo.core.data

import ba.grbo.core.domain.dm.Weather

interface WeatherSource {
    suspend fun get(latitude: String, longitude: String): Weather
}