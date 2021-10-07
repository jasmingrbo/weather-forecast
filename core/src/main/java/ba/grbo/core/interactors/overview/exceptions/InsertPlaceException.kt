package ba.grbo.core.interactors.overview.exceptions

import ba.grbo.core.domain.dm.Locatable
import ba.grbo.core.domain.dm.Place

class InsertPlaceException(
    locatable: Locatable,
    cause: Exception? = null
) : Exception("DB Error, unable to insert Place with Locatable: $locatable", cause) {
    constructor(place: Place): this(place as Locatable)
}