package ba.grbo.weatherforecast.framework.data.dto.remote.openweather

import ba.grbo.core.domain.DEFAULT
import ba.grbo.core.domain.dm.CurrentWeather
import ba.grbo.core.domain.dm.Temperature
import ba.grbo.core.domain.dm.Wind
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.DescriptionAndIcon
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.Precipitation
import ba.grbo.weatherforecast.framework.data.source.mapper.Mapper
import ba.grbo.weatherforecast.framework.mics.toFormattedDateAndTime
import com.squareup.moshi.Json
import kotlin.math.roundToInt
import ba.grbo.core.domain.dm.Precipitation as DomainPrecipitation

data class NetworkCurrentWeather(
    @Json(name = "dt")
    val timestamp: Double,

    @Json(name = "temp")
    val temperature: Double,

    @Json(name = "feels_like")
    val feelsLikeTemperature: Double,

    @Json(name = "pressure")
    val pressure: Double,

    @Json(name = "humidity")
    val humidity: Double,

    @Json(name = "dew_point")
    val dewPoint: Double,

    @Json(name = "uvi")
    val uvi: Double,

    val visibility: Double,

    @Json(name = "wind_speed")
    val windSpeed: Double,

    @Json(name = "wind_deg")
    val windDegrees: Double,

    @Precipitation
    val rain: Double?,

    @Precipitation
    val snow: Double?,

    @DescriptionAndIcon
    @Json(name = "weather")
    val weatherInfo: NetworkWeatherInfo,
) : Mapper<CurrentWeather> {
    override fun toDomain() = CurrentWeather(
        date = timestamp.toFormattedDateAndTime(),
        temperature = Temperature(
            measured = temperature.roundToInt(),
            feelsLike = feelsLikeTemperature.roundToInt()
        ),
        pressure = pressure.roundToInt(),
        humidity = humidity.roundToInt(),
        dewPoint = dewPoint.roundToInt(),
        uvi = uvi,
        visibility = visibility.roundToInt(),
        wind = Wind(speed = windSpeed, degrees = windDegrees),
        precipitation = DomainPrecipitation(
            rain = rain ?: Double.DEFAULT,
            snow = snow ?: Double.DEFAULT
        ),
        description = weatherInfo.description,
        icon = weatherInfo.icon
    )
}