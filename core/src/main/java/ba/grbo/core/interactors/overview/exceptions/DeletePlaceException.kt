package ba.grbo.core.interactors.overview.exceptions

class DeletePlaceException(
    id: Long,
    cause: Exception? = null
) : Exception("DB Error, unable to delete Place with id: $id", cause)