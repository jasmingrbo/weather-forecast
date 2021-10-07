package ba.grbo.core.interactors.overview.exceptions

class FetchLatestPlaceException(
    cause: Exception
): Exception("DB Error, unable to fetch latest Place", cause)