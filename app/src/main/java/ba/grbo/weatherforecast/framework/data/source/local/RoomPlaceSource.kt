package ba.grbo.weatherforecast.framework.data.source.local

import androidx.room.Transaction
import ba.grbo.core.data.PlaceSource
import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.domain.dm.Place
import ba.grbo.weatherforecast.framework.data.dto.local.CacheBasicPlace
import ba.grbo.weatherforecast.framework.data.dto.local.CachedPlace
import ba.grbo.weatherforecast.framework.data.source.mapper.Mappers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomPlaceSource @Inject constructor(
    private val dao: PlaceDao,
    private val mappers: Mappers,
    private val dispatcher: CoroutineDispatcher,
) : PlaceSource {
    @Transaction
    override suspend fun insert(place: Place) = withContext(dispatcher) {
        dao.insert(place.toData())
        exists(place.coordinates)
    }

    @Transaction
    override suspend fun update(place: Place) = withContext(dispatcher) {
        dao.update(place.toData())
        get(place.id) == place
    }

    @Transaction
    override suspend fun update(places: List<Place>) = withContext(dispatcher) {
        dao.update(places.toData())
        dao.get().toDomain().all { place -> place in places }
    }

    @Transaction
    override suspend fun delete(id: Long) = withContext(dispatcher) {
        dao.delete(id)
        !dao.exists(id)
    }

    override suspend fun exists(coordinates: Coordinates) = withContext(dispatcher) {
        dao.exists(coordinates.toData())
    }

    override suspend fun get(id: Long) = withContext(dispatcher) { dao.get(id).toDomain() }

    override suspend fun get() = withContext(dispatcher) { dao.get().toDomain() }

    override suspend fun getLatest() = withContext(dispatcher) { dao.getLatest().toDomain() }

    override fun observe(id: Long) = dao.observe(id).map { place -> place.toDomain() }

    override fun observeObserved() = dao.observe().map { places -> places.toDomain() }

    private fun Place.toData() = mappers.first.toData(this)

    private fun CachedPlace.toDomain() = mappers.first.toDomain(this)

    private fun Coordinates.toData() = mappers.third.toData(this)

    private fun Iterable<CachedPlace>.toDomain() = map { mappers.first.toDomain(it) }

    private fun Iterable<Place>.toData() = map { mappers.first.toData(it) }

    private fun List<CacheBasicPlace>.toDomain() = map { mappers.second.toDomain(it) }
}