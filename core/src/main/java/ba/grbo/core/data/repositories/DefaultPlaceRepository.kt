package ba.grbo.core.data.repositories

import ba.grbo.core.data.PlaceSource
import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.domain.dm.Place

class DefaultPlaceRepository(private val source: PlaceSource) : PlaceRepository {
    override suspend fun insertPlace(place: Place) = source.insert(place)

    override suspend fun updatePlace(place: Place) = source.update(place)

    override suspend fun updatePlaces(places: List<Place>) = source.update(places)

    override suspend fun deletePlace(id: Long) = source.delete(id)

    override suspend fun doesPlaceExist(coordinates: Coordinates) = source.exists(coordinates)

    override suspend fun getPlace(id: Long) = source.get(id)

    override suspend fun getLatestPlace() = source.getLatest()

    override suspend fun getObservedPlacesAsPlaces() = source.get()

    override fun observePlace(id: Long) = source.observe(id)

    override fun observeBasicPlaces() = source.observeObserved()
}