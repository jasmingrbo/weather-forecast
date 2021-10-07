package ba.grbo.core.data

import ba.grbo.core.domain.dm.Location

interface LocationSource {
    suspend fun get(query: String): List<Location>
}