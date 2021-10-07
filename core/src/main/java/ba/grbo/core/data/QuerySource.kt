package ba.grbo.core.data

import kotlinx.coroutines.flow.Flow

interface QuerySource {
    suspend fun insert(query: String): Boolean

    fun observe(): Flow<List<String>>

    suspend fun clear()
}