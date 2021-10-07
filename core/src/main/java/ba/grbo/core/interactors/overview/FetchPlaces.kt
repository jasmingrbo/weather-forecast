package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.PlaceRepository
import ba.grbo.core.interactors.overview.exceptions.FetchPlacesException

class FetchPlaces(
    private val repository: PlaceRepository
) {
    suspend operator fun invoke() = try {
        repository.getObservedPlacesAsPlaces()
    } catch (e: Exception) {
        throw FetchPlacesException(e)
    }
}