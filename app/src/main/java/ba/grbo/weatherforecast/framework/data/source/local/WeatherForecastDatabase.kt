package ba.grbo.weatherforecast.framework.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ba.grbo.weatherforecast.framework.data.dto.local.CachedPlace
import ba.grbo.weatherforecast.framework.data.dto.local.CachedQuery

@Database(entities = [CachedPlace::class, CachedQuery::class], version = 1)
abstract class WeatherForecastDatabase : RoomDatabase() {
    abstract val placeDao: PlaceDao
    abstract val queryDao: QueryDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: WeatherForecastDatabase

        private const val DB_NAME = "weather_forecast_database"

        fun getInstance(context: Context) = if (::INSTANCE.isInitialized) INSTANCE
        else synchronized(this) { buildDatabase(context).also { INSTANCE = it } }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            WeatherForecastDatabase::class.java,
            DB_NAME
        ).build()
    }
}