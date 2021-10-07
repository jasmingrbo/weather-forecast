package ba.grbo.weatherforecast.framework.data.dto.remote.openweather

import ba.grbo.core.domain.dm.DailyForecast
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.Icon
import ba.grbo.weatherforecast.framework.data.source.mapper.Mapper
import ba.grbo.weatherforecast.framework.mics.toFormattedDate
import com.squareup.moshi.Json

data class NetworkDailyForecast(
    @Json(name = "dt")
    val timestamp: Double,

    @Json(name = "temp")
    val temperature: NetworkTemperatureRange,

    @Json(name = "pop")
    val precipitationProbability: Double,

    @Icon
    @Json(name = "weather")
    val weatherIcon: String,
) : Mapper<DailyForecast> {
    override fun toDomain() = DailyForecast(
        date = timestamp.toFormattedDate(),
        temperature = temperature.toDomain(),
        precipitationProbability = precipitationProbability,
        icon = weatherIcon
    )
}