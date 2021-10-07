package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.LocationRepository
import ba.grbo.core.domain.dm.Result.SourceResult.Failure
import ba.grbo.core.domain.dm.Result.Loading
import ba.grbo.core.domain.dm.Result.SourceResult.Success
import ba.grbo.core.interactors.overview.exceptions.FetchLocationsException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchLocations(
    private val repository: LocationRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    operator fun invoke(query: String) = flow {
        emit(Loading(true))

        try {
            emit(Success(repository.getLocations(query)))
        } catch (e: Exception) {
            emit(Failure(FetchLocationsException(query, e)))
        } finally {
            emit(Loading(false))
        }
    }.flowOn(dispatcher)
}