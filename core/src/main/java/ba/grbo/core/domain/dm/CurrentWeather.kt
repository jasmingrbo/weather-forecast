package ba.grbo.core.domain.dm

data class CurrentWeather(
    val date: String,
    val temperature: Temperature,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Int,
    val uvi: Double,
    val visibility: Int,
    val wind: Wind,
    val precipitation: Precipitation,
    val description: String,
    val icon: String
) {
    fun simplify() = BasicCurrentWeather(
        date = date,
        temperature = temperature.measured,
        humidity = humidity,
        uvi = uvi,
        visibility = visibility,
        wind = wind,
        description = description,
        icon = icon
    )

    companion object {
        val DEFAULT = CurrentWeather(
            date = "October 12, 12:00",
            temperature = Temperature.DEFAULT,
            pressure = 1000,
            humidity = 100,
            dewPoint = 4,
            uvi = 1.2,
            visibility = 10000,
            wind = Wind.DEFAULT,
            precipitation = Precipitation.DEFAULT,
            description = "Broken clouds",
            icon = "800d"
        )
    }
}