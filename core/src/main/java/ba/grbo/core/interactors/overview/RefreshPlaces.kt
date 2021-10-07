package ba.grbo.core.interactors.overview

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class RefreshPlaces(
    private val fetchPlaces: FetchPlaces,
    private val refreshPlace: RefreshPlace,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke() = flow {
        try {
            val places = fetchPlaces()

            // if one refresh fails, we don't want to interrupt everything
            supervisorScope {
                places.forEach { place ->
                    launch { refreshPlace(place) }
                }
            }

        } catch (e: Exception) {
            emit(e)
        }
    }.flowOn(dispatcher)
}