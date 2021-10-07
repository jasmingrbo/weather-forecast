package ba.grbo.weatherforecast.framework.data.dto.local

import androidx.room.ColumnInfo
import ba.grbo.core.domain.DEFAULT
import ba.grbo.weatherforecast.framework.data.dto.local.CachedPlace.Companion.AIR_POLLUTION
import ba.grbo.weatherforecast.framework.data.dto.local.CachedPlace.Companion.COUNTRY_CODE
import ba.grbo.weatherforecast.framework.data.dto.local.CachedPlace.Companion.CURRENT_WEATHER
import ba.grbo.weatherforecast.framework.data.dto.local.CachedPlace.Companion.REFRESH_FAILED
import ba.grbo.weatherforecast.framework.data.dto.local.CachedPlace.Companion.WEATHER_ALERTS
import ba.grbo.weatherforecast.framework.data.dto.local.CachedPlace.Companion.WEATHER_DATA_STALE
import ba.grbo.weatherforecast.framework.mics.EMPTY

data class CacheBasicPlace(
    val id: Long,

    val coordinates: String,

    val name: String,

    @ColumnInfo(name = COUNTRY_CODE)
    val countryCode: String,

    @ColumnInfo(name = CURRENT_WEATHER)
    val currentWeather: String,

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
        val DEFAULT = CacheBasicPlace(
            id= Long.DEFAULT,
            coordinates = String.EMPTY,
            name = String.EMPTY,
            countryCode = String.EMPTY,
            currentWeather = String.EMPTY,
            weatherAlert = String.EMPTY,
            airPollution = String.EMPTY,
            weatherDataStale = false,
            refreshFailed = false
        )
    }
}