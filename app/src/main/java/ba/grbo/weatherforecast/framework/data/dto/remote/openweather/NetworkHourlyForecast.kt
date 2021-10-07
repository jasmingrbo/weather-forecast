package ba.grbo.weatherforecast.framework.data.dto.remote.openweather

import ba.grbo.core.domain.dm.HourlyForecast
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.Icon
import ba.grbo.weatherforecast.framework.data.source.mapper.Mapper
import ba.grbo.weatherforecast.framework.mics.toFormattedTime
import com.squareup.moshi.Json
import kotlin.math.roundToInt

data class NetworkHourlyForecast(
    @Json(name = "dt")
    val timestamp: Double,

    @Json(name = "temp")
    val temperature: Double,

    @Json(name = "pop")
    val precipitationProbability: Double,

    @Icon
    @Json(name = "weather")
    val weatherIcon: String,
) : Mapper<HourlyForecast> {
    override fun toDomain() = HourlyForecast(
        time = timestamp.toFormattedTime(),
        temperature = temperature.roundToInt(),
        precipitationProbability = precipitationProbability,
        weatherIcon = weatherIcon
    )
}