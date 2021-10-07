package ba.grbo.weatherforecast.framework.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import ba.grbo.weatherforecast.framework.data.dto.local.CacheBasicPlace
import ba.grbo.weatherforecast.framework.data.dto.local.CachedPlace
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(place: CachedPlace): Long

    @Update
    suspend fun update(place: CachedPlace): Int

    @Update
    suspend fun update(places: List<CachedPlace>): Int

    @Query("DELETE FROM places_table WHERE id == :id")
    suspend fun delete(id: Long)

    @Query("SELECT EXISTS(SELECT * FROM places_table WHERE id == :id)")
    suspend fun exists(id: Long): Boolean

    @Query("SELECT EXISTS(SELECT * FROM places_table WHERE coordinates == :coordinates)")
    suspend fun exists(coordinates: String): Boolean

    @Query("SELECT * FROM places_table WHERE id == :id")
    fun get(id: Long): CachedPlace

    @Query("SELECT * FROM places_table")
    fun get(): List<CachedPlace>

    @Query("SELECT * FROM places_table ORDER BY id DESC LIMIT 1")
    fun getLatest(): CachedPlace

    @Query("SELECT * FROM places_table WHERE id == :id")
    fun observe(id: Long): Flow<CachedPlace>

    @Query("""SELECT id,
                     coordinates, 
                     name, 
                     country_code, 
                     current_weather, 
                     weather_alerts, 
                     air_pollution,
                     weather_data_stale,
                     refresh_failed
               FROM places_table ORDER BY id DESC""")
    fun observe(): Flow<List<CacheBasicPlace>>
}