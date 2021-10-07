package ba.grbo.core.domain.dm

data class BasicWeather(
    val current: BasicCurrentWeather,
    val hasAlert: Boolean,
    val stale: Boolean
)