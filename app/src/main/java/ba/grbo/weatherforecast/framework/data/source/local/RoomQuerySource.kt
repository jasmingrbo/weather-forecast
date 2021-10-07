package ba.grbo.weatherforecast.framework.data.source.local

import androidx.room.Transaction
import ba.grbo.core.data.QuerySource
import ba.grbo.weatherforecast.framework.mics.toData
import ba.grbo.weatherforecast.framework.mics.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomQuerySource @Inject constructor (
    private val dao: QueryDao,
    private val dispatcher: CoroutineDispatcher,
) : QuerySource {
    @Transaction
    override suspend fun insert(query: String) = withContext(dispatcher) {
        dao.insert(query.toData())
        dao.get(query).value == query
    }

    override fun observe() = dao.observe().toDomain()

    override suspend fun clear() = withContext(dispatcher) { dao.clear() }
}