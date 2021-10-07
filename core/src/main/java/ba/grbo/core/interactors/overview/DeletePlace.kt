package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.PlaceRepository
import ba.grbo.core.interactors.overview.exceptions.DeletePlaceException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DeletePlace(
    private val repository: PlaceRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(id: Long) = flow {
        try {
            if (!repository.deletePlace(id)) throw DeletePlaceException(id)
        } catch (e: Exception) {
            emit(if (e is DeletePlaceException) e else DeletePlaceException(id, e))
        }
    }.flowOn(dispatcher)
}