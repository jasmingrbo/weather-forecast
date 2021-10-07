package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.PlaceRepository
import ba.grbo.core.domain.dm.Result.SourceResult.Failure
import ba.grbo.core.domain.dm.Result.SourceResult.Success
import ba.grbo.core.interactors.overview.exceptions.ObserveBasicPlacesException

class ObserveBasicPlaces(private val repository: PlaceRepository) {
    operator fun invoke() = try {
        Success(repository.observeBasicPlaces())
    } catch (e: Exception) {
        Failure(ObserveBasicPlacesException(e))
    }
}