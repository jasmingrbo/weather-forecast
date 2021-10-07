package ba.grbo.core.domain.dm

data class Forecast(
    val hourly: List<HourlyForecast>,
    val daily: List<DailyForecast>,
) {
    companion object {
        val DEFAULT = Forecast(
            hourly = List(48) { HourlyForecast.DEFAULT },
            daily = List(8) { DailyForecast.DEFAULT }
        )
    }
}