package ba.grbo.core.domain.dm

import ba.grbo.core.domain.DEFAULT

data class DailyForecast(
    val date: String,
    val temperature: TemperatureRange,
    val precipitationProbability: Double,
    val icon: String
) {

    companion object {
        val DEFAULT = DailyForecast(
            date = String.DEFAULT,
            temperature = TemperatureRange.DEFAULT,
            precipitationProbability = Double.DEFAULT,
            icon = String.DEFAULT
        )
    }
}