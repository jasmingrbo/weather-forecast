package ba.grbo.weatherforecast.framework.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ba.grbo.weatherforecast.framework.data.dto.local.CachedQuery
import kotlinx.coroutines.flow.Flow

@Dao
interface QueryDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(query: CachedQuery): Long

    @Query("SELECT * FROM queries_table ORDER BY id DESC")
    fun observe(): Flow<List<CachedQuery>>

    @Query("SELECT * FROM queries_table WHERE value == :query")
    suspend fun get(query: String): CachedQuery

    @Query("SELECT * FROM queries_table")
    suspend fun get(): List<CachedQuery>

    @Query("DELETE FROM queries_table")
    suspend fun clear()
}