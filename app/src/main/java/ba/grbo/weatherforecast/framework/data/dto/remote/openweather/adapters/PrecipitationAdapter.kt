package ba.grbo.weatherforecast.framework.data.dto.remote.openweather.adapters

import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.Precipitation
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

@Suppress("UNUSED")
object PrecipitationAdapter {
    private const val ONE_HOUR = "1h"

    @FromJson
    @Precipitation
    fun fromJson(precipitation: Map<String, Any>): Double? = precipitation[ONE_HOUR] as Double?

    @ToJson
    fun toJson(@Precipitation precipitation: Double?): Map<String, Any?> {
        return mapOf(ONE_HOUR to precipitation)
    }
}