package ba.grbo.core.data

import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.core.domain.dm.Place
import kotlinx.coroutines.flow.Flow

interface PlaceSource {
    suspend fun insert(place: Place): Boolean

    suspend fun update(place: Place): Boolean

    suspend fun update(places: List<Place>): Boolean

    suspend fun delete(id: Long): Boolean

    suspend fun exists(coordinates: Coordinates): Boolean

    suspend fun get(id: Long): Place

    suspend fun get(): List<Place>

    suspend fun getLatest(): Place

    fun observe(id: Long): Flow<Place>

    fun observeObserved(): Flow<List<BasicPlace>>
}