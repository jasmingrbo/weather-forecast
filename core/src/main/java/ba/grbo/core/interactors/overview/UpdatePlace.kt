package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.PlaceRepository
import ba.grbo.core.domain.dm.Place
import ba.grbo.core.interactors.overview.exceptions.UpdatePlaceException

class UpdatePlace(private val repository: PlaceRepository) {
    suspend operator fun invoke(place: Place) {
        try {
            if (!repository.updatePlace(place)) throw UpdatePlaceException(place)
        } catch (e: Exception) {
            throw UpdatePlaceException(place, e)
        }
    }
}