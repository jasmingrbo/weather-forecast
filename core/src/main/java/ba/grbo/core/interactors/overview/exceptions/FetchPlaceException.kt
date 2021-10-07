package ba.grbo.core.interactors.overview.exceptions

class FetchPlaceException(
    id: Long,
    cause: Exception
) : Exception("DB Error, unable to fetch Place with the id: $id", cause)