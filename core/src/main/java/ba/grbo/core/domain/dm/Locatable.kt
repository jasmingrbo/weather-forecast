package ba.grbo.core.domain.dm

interface Locatable {
    val coordinates: Coordinates
    val name: String
    val address: String
    val countryFlagResource: Int
}