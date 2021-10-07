package ba.grbo.core.data.repositories

import ba.grbo.core.domain.dm.Location

interface LocationRepository {
    suspend fun getLocations(query: String): List<Location>
}