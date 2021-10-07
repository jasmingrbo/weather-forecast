package ba.grbo.weatherforecast.framework.data.dto.remote.openweather

import ba.grbo.core.domain.dm.AirPollution
import ba.grbo.core.domain.dm.AirPollution.AirComponents
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.AirPollutionData
import ba.grbo.weatherforecast.framework.data.source.mapper.Mapper
import com.squareup.moshi.Json

data class NetworkAirPollution(
    @Json(name = "list")
    @AirPollutionData
    val data: NetworkAirPollutionData,
) : Mapper<AirPollution> {
    override fun toDomain() = AirPollution(
        airQualityIndex = data.airQualityIndex.toInt(),
        components = AirComponents(
            carbonMonoxide = data.carbonMonoxide,
            nitrogenMonoxide = data.nitrogenMonoxide,
            nitrogenDioxide = data.nitrogenDioxide,
            ozone = data.ozone,
            sulphurDioxide = data.sulphurDioxide,
            fineParticles = data.fineParticles,
            coarseParticles = data.coarseParticles,
            ammonia = data.ammonia
        )
    )

    data class NetworkAirPollutionData(
        val airQualityIndex: Double,
        val carbonMonoxide: Double,
        val nitrogenMonoxide: Double,
        val nitrogenDioxide: Double,
        val ozone: Double,
        val sulphurDioxide: Double,
        val fineParticles: Double,
        val coarseParticles: Double,
        val ammonia: Double,
    )
}
