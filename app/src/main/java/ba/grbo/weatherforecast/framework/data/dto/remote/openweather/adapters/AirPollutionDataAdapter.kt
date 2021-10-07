package ba.grbo.weatherforecast.framework.data.dto.remote.openweather.adapters

import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.NetworkAirPollution.NetworkAirPollutionData
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.AirPollutionData
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

@Suppress("UNUSED")
object AirPollutionDataAdapter {
    private const val MAIN = "main"
    private const val AQI = "aqi"

    private const val COMPONENTS = "components"
    private const val CO = "co"
    private const val NO = "no"
    private const val NO2 = "no2"
    private const val O3 = "o3"
    private const val SO2 = "so2"
    private const val PM2_5 = "pm2_5"
    private const val PM10 = "pm10"
    private const val NH3 = "nh3"

    @FromJson
    @AirPollutionData
    fun fromJson(data: List<Map<String, Any>>): NetworkAirPollutionData {
        val main = data[0][MAIN] as Map<*, *>
        val components = data[0][COMPONENTS] as Map<*, *>
        return NetworkAirPollutionData(
            airQualityIndex = main[AQI] as Double,
            carbonMonoxide = components[CO] as Double,
            nitrogenMonoxide = components[NO] as Double,
            nitrogenDioxide = components[NO2] as Double,
            ozone = components[O3] as Double,
            sulphurDioxide = components[SO2] as Double,
            fineParticles = components[PM2_5] as Double,
            coarseParticles = components[PM10] as Double,
            ammonia = components[NH3] as Double
        )
    }

    @ToJson
    fun toJson(@AirPollutionData data: NetworkAirPollutionData) = listOf(
        mapOf(
            MAIN to mapOf(AQI to data.airQualityIndex),
            COMPONENTS to mapOf(
                CO to data.carbonMonoxide,
                NO to data.nitrogenMonoxide,
                NO2 to data.nitrogenDioxide,
                O3 to data.ozone,
                SO2 to data.sulphurDioxide,
                PM2_5 to data.fineParticles,
                PM10 to data.coarseParticles,
                NH3 to data.ammonia
            )
        )
    )
}