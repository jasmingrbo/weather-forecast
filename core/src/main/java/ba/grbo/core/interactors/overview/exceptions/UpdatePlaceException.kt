package ba.grbo.core.interactors.overview.exceptions

import ba.grbo.core.domain.dm.Place

class UpdatePlaceException(
    place: Place,
    cause: Exception? = null,
): Exception("DB Error, unable to update Place: $place", cause)