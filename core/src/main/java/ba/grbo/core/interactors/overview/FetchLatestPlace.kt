package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.PlaceRepository
import ba.grbo.core.interactors.overview.exceptions.FetchLatestPlaceException

class FetchLatestPlace(private val repository: PlaceRepository) {
    suspend operator fun invoke() = try {
        repository.getLatestPlace()
    } catch (e: Exception) {
        throw FetchLatestPlaceException(e)
    }
}