package ba.grbo.core.domain.dm

data class Precipitation(
    val rain: Double,
    val snow: Double
) {
    companion object {
        val DEFAULT = Precipitation(rain = 0.0, snow = 0.0)
    }
}