package ba.grbo.weatherforecast.framework.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = CachedPlace.TABLE_NAME,
    indices = [Index(value = ["coordinates"], unique = true)]
)
data class CachedPlace(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val coordinates: String,

    val name: String,

    val address: String,

    @ColumnInfo(name = COUNTRY_CODE)
    val countryCode: String,

    @ColumnInfo(name = CURRENT_WEATHER)
    val currentWeather: String,

    @ColumnInfo(name = HOURLY_FORECAST)
    val hourlyForecast: String,

    @ColumnInfo(name = DAILY_FORECAST)
    val dailyForecast: String,

    @ColumnInfo(name = WEATHER_ALERTS)
    val weatherAlert: String,

    @ColumnInfo(name = AIR_POLLUTION)
    val airPollution: String,

    @ColumnInfo(name = WEATHER_DATA_STALE)
    val weatherDataStale: Boolean,

    @ColumnInfo(name = REFRESH_FAILED)
    val refreshFailed: Boolean
) {
    companion object {
        const val TABLE_NAME = "places_table"
        const val COUNTRY_CODE = "country_code"
        const val CURRENT_WEATHER = "current_weather"
        const val HOURLY_FORECAST = "hourly_forecast"
        const val DAILY_FORECAST = "daily_forecast"
        const val WEATHER_ALERTS = "weather_alerts"
        const val AIR_POLLUTION = "air_pollution"
        const val WEATHER_DATA_STALE = "weather_data_stale"
        const val REFRESH_FAILED = "refresh_failed"
    }
}