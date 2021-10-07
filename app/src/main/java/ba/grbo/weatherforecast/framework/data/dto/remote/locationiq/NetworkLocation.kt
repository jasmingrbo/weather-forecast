package ba.grbo.weatherforecast.framework.data.dto.remote.locationiq

import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.domain.dm.Location
import ba.grbo.weatherforecast.R
import ba.grbo.weatherforecast.framework.data.dto.remote.locationiq.qualifiers.CountryCode
import ba.grbo.weatherforecast.framework.data.source.mapper.Mapper
import ba.grbo.weatherforecast.framework.mics.Country
import com.squareup.moshi.Json

data class NetworkLocation(
    @Json(name = "lat")
    val latitude: String,

    @Json(name = "lon")
    val longitude: String,

    @Json(name = "display_place")
    val name: String,

    @Json(name = "display_address")
    val address: String,

    @CountryCode
    @Json(name = "address")
    val countryCode: String?,
) : Mapper<Location> {
    override fun toDomain() = Location(
        coordinates = Coordinates(latitude, longitude),
        name = name,
        address = address,
        countryFlagResource = countryFlagResource
    )

    private val countryFlagResource: Int
        get() = if (countryCode == null) R.drawable.ic_unknown_flag
        else Country.getFlagResource(countryCode)
}