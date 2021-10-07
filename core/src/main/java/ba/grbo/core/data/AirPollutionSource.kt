package ba.grbo.core.data

import ba.grbo.core.domain.dm.AirPollution

interface AirPollutionSource {
    suspend fun get(latitude: String, longitude: String): AirPollution
}