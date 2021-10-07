package ba.grbo.core.domain.dm

data class Location(
    override val coordinates: Coordinates,
    override val name: String,
    override val address: String,
    override val countryFlagResource: Int
): Locatable