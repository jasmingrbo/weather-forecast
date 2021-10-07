package ba.grbo.core.domain.dm

data class BasicPlace(
    val id: Long,
    val coordinates: Coordinates,
    val name: String,
    val countryFlagResource: Int,
    val weather: BasicWeather,
    val airQualityIndex: Int
)