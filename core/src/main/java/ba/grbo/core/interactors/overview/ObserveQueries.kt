package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.QueryRepository
import ba.grbo.core.domain.dm.Result
import ba.grbo.core.domain.dm.Result.SourceResult.Failure
import ba.grbo.core.domain.dm.Result.SourceResult.Success
import ba.grbo.core.interactors.overview.exceptions.ObserveQueriesException
import com.sun.net.httpserver.Authenticator
import kotlinx.coroutines.CoroutineDispatcher

class ObserveQueries(private val repository: QueryRepository) {
    operator fun invoke() = try {
        Success(repository.observeQueries())
    } catch (e: Exception) {
        Failure(ObserveQueriesException(e))
    }
}