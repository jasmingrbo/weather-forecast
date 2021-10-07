package ba.grbo.core.data.repositories

import kotlinx.coroutines.flow.Flow

interface QueryRepository {
    suspend fun insertQuery(query: String): Boolean

    fun observeQueries(): Flow<List<String>>

    suspend fun clear()
}