package ba.grbo.core.interactors.overview.exceptions

class InsertQueryException(
    query: String,
    cause: Exception? = null,
) : Exception("DB Error, unable to insert Query: $query", cause)