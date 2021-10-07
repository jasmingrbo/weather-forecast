package ba.grbo.core.interactors.overview.exceptions

class ObserveBasicPlacesException(
    cause: Exception
): Exception("DB Error, unable to start observation of BasicPlaces.", cause)