package ba.grbo.core.interactors.overview.exceptions

class FetchQueriesException(
    cause: Throwable
) : Exception("DB Error,unable to fetch Queries.", cause)