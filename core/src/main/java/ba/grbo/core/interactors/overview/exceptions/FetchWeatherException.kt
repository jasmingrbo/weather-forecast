package ba.grbo.core.interactors.overview.exceptions

import ba.grbo.core.domain.dm.Coordinates

class FetchWeatherException(
    coordinates: Coordinates,
    cause: Exception,
) : Exception(
    "Network Error, unable to fetch Weather for Place with coordinates: $coordinates",
    cause
)