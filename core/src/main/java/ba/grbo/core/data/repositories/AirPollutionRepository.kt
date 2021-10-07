package ba.grbo.core.data.repositories

import ba.grbo.core.domain.dm.AirPollution

interface AirPollutionRepository {
    suspend fun getAirPollution(latitude: String, longitude: String): AirPollution
}