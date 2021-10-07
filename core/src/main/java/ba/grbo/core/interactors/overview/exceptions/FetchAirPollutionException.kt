package ba.grbo.core.interactors.overview.exceptions

import ba.grbo.core.domain.dm.Coordinates

class FetchAirPollutionException(
    coordinates: Coordinates,
    cause: Exception
): Exception(
    "Network Error, unable to fetch AirPollution for Place with coordinates: $coordinates",
    cause
)