package ba.grbo.core.domain.dm

data class AirPollution(
    val airQualityIndex: Int,
    val components: AirComponents
) {
    data class AirComponents(
        val carbonMonoxide: Double,
        val nitrogenMonoxide: Double,
        val nitrogenDioxide: Double,
        val ozone: Double,
        val sulphurDioxide: Double,
        val fineParticles: Double,
        val coarseParticles: Double,
        val ammonia: Double
    )

    companion object {
        val DEFAULT = AirPollution(
            airQualityIndex = 1,
            components = AirComponents(
                carbonMonoxide = 0.0,
                nitrogenMonoxide = 0.0,
                nitrogenDioxide = 0.0,
                ozone = 0.0,
                sulphurDioxide = 0.0,
                fineParticles = 0.0,
                coarseParticles = 0.0,
                ammonia = 0.0
            )
        )
    }
}