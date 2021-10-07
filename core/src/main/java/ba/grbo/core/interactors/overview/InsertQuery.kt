package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.QueryRepository
import ba.grbo.core.domain.dm.Result.Loading
import ba.grbo.core.domain.dm.Result.SourceResult.Failure
import ba.grbo.core.interactors.overview.exceptions.InsertQueryException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InsertQuery(
    private val repository: QueryRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(query: String) = flow {
        emit(Loading(true))

        try {
            if (!repository.insertQuery(query)) throw InsertQueryException(query)
        } catch (e: Exception) {
            emit(Failure(if (e is InsertQueryException) e else InsertQueryException(query, e)))
        }

    }.flowOn(dispatcher)
}