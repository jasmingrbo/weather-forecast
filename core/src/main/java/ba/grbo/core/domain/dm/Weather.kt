package ba.grbo.core.domain.dm

data class Weather(
    val current: CurrentWeather,
    val forecast: Forecast,
    val alert: Alert,
    val stale: Boolean
) {
    companion object {
        val DEFAULT = Weather(
            current = CurrentWeather.DEFAULT,
            forecast = Forecast.DEFAULT,
            alert = Alert.DEFAULT,
            stale = true
        )
    }
}
