package ba.grbo.core.interactors.overview.exceptions

class ObserveQueriesException(
    cause: Exception
): Exception("DB Error, unable to start observation of Queries.", cause)