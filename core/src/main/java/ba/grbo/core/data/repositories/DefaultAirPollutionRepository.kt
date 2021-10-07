package ba.grbo.core.data.repositories

import ba.grbo.core.data.AirPollutionSource

class DefaultAirPollutionRepository(
    private val source: AirPollutionSource,
) : AirPollutionRepository {
    override suspend fun getAirPollution(
        latitude: String,
        longitude: String,
    ) = source.get(latitude, longitude)
}