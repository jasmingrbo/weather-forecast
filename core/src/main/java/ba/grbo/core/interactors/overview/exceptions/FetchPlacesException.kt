package ba.grbo.core.interactors.overview.exceptions

class FetchPlacesException(
    cause: Exception
): Exception("DB Error, unable to fetch Places", cause)