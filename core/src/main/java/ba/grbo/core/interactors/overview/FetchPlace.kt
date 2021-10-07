package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.PlaceRepository
import ba.grbo.core.interactors.overview.exceptions.FetchPlaceException

class FetchPlace(
    private val repository: PlaceRepository
) {
    suspend operator fun invoke(id: Long) = try {
        repository.getPlace(id)
    } catch (e: Exception) {
        throw FetchPlaceException(id, e)
    }
}