package ba.grbo.weatherforecast.framework.data.dto.remote.openweather

import ba.grbo.core.domain.dm.TemperatureRange
import ba.grbo.weatherforecast.framework.data.source.mapper.Mapper
import com.squareup.moshi.Json
import kotlin.math.roundToInt

data class NetworkTemperatureRange(
    @Json(name = "min")
    val minimum: Double,

    @Json(name = "max")
    val maximum: Double,
) : Mapper<TemperatureRange> {
    override fun toDomain() = TemperatureRange(
        minimum = minimum.roundToInt(),
        maximum = maximum.roundToInt()
    )
}
