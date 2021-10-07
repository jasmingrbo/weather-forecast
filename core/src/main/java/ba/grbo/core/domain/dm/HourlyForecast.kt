package ba.grbo.core.domain.dm

import ba.grbo.core.domain.DEFAULT

data class HourlyForecast(
    val time: String,
    val temperature: Int,
    val precipitationProbability: Double,
    val weatherIcon: String
) {
    companion object {
        val DEFAULT = HourlyForecast(
            time = String.DEFAULT,
            temperature = Int.DEFAULT,
            precipitationProbability = Double.DEFAULT,
            weatherIcon = String.DEFAULT
        )
    }
}