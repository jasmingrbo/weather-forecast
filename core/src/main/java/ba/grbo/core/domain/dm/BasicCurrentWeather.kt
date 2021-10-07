package ba.grbo.core.domain.dm

data class BasicCurrentWeather(
    val date: String,
    val temperature: Int,
    val humidity: Int,
    val uvi: Double,
    val visibility: Int,
    val wind: Wind,
    val description: String,
    val icon: String
)