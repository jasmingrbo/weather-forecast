package ba.grbo.weatherforecast.framework.data.dto.remote.openweather.adapters

import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.Icon
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

@Suppress("UNUSED")
object IconAdapter {
    private const val ICON = "icon"

    @FromJson
    @Icon
    fun fromJson(weather: List<Map<String, Any>>) = weather[0][ICON] as String

    @ToJson
    fun toJson(@Icon icon: String): List<Map<String, Any>> {
        return listOf(mapOf(ICON to icon))
    }
}