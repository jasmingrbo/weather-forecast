package ba.grbo.core.data.repositories

import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.core.domain.dm.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    suspend fun insertPlace(place: Place): Boolean

    suspend fun updatePlace(place: Place): Boolean

    suspend fun updatePlaces(places: List<Place>): Boolean

    suspend fun deletePlace(id: Long): Boolean

    suspend fun doesPlaceExist(coordinates: Coordinates): Boolean

    suspend fun getPlace(id: Long): Place

    suspend fun getObservedPlacesAsPlaces(): List<Place>

    suspend fun getLatestPlace(): Place

    fun observePlace(id: Long): Flow<Place>

    fun observeBasicPlaces(): Flow<List<BasicPlace>>
}