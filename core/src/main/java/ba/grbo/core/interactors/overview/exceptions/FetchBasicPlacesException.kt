package ba.grbo.core.interactors.overview.exceptions

class FetchBasicPlacesException(
    cause: Throwable
) : Exception("DB Error,unable to fetch BasicPlaces.", cause)