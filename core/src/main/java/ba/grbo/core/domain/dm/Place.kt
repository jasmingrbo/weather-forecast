package ba.grbo.core.domain.dm

import ba.grbo.core.domain.DEFAULT

data class Place(
    val id: Long,
    override val coordinates: Coordinates,
    override val name: String,
    override val address: String,
    override val countryFlagResource: Int,
    val weather: Weather,
    val airPollution: AirPollution,
    val refreshFailed: Boolean,
) : Locatable {
    constructor(
        id: Long,
        locatable: Locatable,
        weather: Weather,
        airPollution: AirPollution,
        refreshFailed: Boolean,
    ) : this(
        id = id,
        coordinates = locatable.coordinates,
        name = locatable.name,
        address = locatable.address,
        countryFlagResource = locatable.countryFlagResource,
        weather = weather,
        airPollution = airPollution,
        refreshFailed = refreshFailed
    )

    companion object {
        fun createDefault(locatable: Locatable) = Place(
            id = Long.DEFAULT, // has to be null
            locatable = locatable,
            weather = Weather.DEFAULT,
            airPollution = AirPollution.DEFAULT,
            refreshFailed = false
        )
    }
}