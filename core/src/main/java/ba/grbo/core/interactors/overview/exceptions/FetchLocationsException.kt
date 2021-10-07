package ba.grbo.core.interactors.overview.exceptions

class FetchLocationsException(
    val query: String,
    cause: Exception
): Exception(
    "Network Error, unable to fetch Locations for query: $query",
    cause
)