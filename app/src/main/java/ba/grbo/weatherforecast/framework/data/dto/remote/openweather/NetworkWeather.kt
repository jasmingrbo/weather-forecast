package ba.grbo.weatherforecast.framework.data.dto.remote.openweather

import ba.grbo.core.domain.dm.Forecast
import ba.grbo.core.domain.dm.Weather
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.Alert
import ba.grbo.weatherforecast.framework.data.source.mapper.Mapper
import ba.grbo.weatherforecast.framework.mics.toDomain
import com.squareup.moshi.Json

data class NetworkWeather(
    val current: NetworkCurrentWeather,

    @Json(name = "hourly")
    val hourlyForecasts: List<NetworkHourlyForecast>,

    @Json(name = "daily")
    val dailyForecasts: List<NetworkDailyForecast>,

    @Json(name = "alerts")
    @Alert
    val alert: NetworkAlert?,
) : Mapper<Weather> {
    override fun toDomain() = Weather(
        current = current.toDomain(),
        forecast = Forecast(
            hourly = hourlyForecasts.toDomain(),
            daily = dailyForecasts.toDomain()
        ),
        alert = alert?.toDomain() ?: NetworkAlert.DEFAULT.toDomain(),
        stale = false
    )
}
