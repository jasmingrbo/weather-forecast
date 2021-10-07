package ba.grbo.weatherforecast.framework.data.dto.remote.openweather.adapters

import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.NetworkWeatherInfo
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.DescriptionAndIcon
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

@Suppress("UNUSED")
object DescriptionAndIconAdapter {
    private const val DESCRIPTION = "description"
    private const val ICON = "icon"

    @FromJson
    @DescriptionAndIcon
    fun fromJson(weathers: List<Map<String, Any>>) = NetworkWeatherInfo(
        (weathers[0][DESCRIPTION] as String).replaceFirstChar { it.titlecase(Locale.getDefault()) },
        weathers[0][ICON] as String
    )

    @ToJson
    fun toJson(@DescriptionAndIcon weather: NetworkWeatherInfo) = listOf(
        mapOf(
            DESCRIPTION to weather.description,
            ICON to weather.icon
        )
    )
}