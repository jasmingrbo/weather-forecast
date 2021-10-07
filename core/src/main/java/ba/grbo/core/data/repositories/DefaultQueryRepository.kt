package ba.grbo.core.data.repositories

import ba.grbo.core.data.QuerySource
import kotlinx.coroutines.flow.Flow

class DefaultQueryRepository(private val source: QuerySource) : QueryRepository {
    override suspend fun insertQuery(query: String) = source.insert(query)

    override fun observeQueries(): Flow<List<String>> = source.observe()

    override suspend fun clear() = source.clear()
}