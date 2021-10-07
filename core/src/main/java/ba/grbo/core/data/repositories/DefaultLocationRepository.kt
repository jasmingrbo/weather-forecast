package ba.grbo.core.data.repositories

import ba.grbo.core.data.LocationSource

class DefaultLocationRepository(private val source: LocationSource): LocationRepository {
    override suspend fun getLocations(query: String) = source.get(query)
}