package ba.grbo.core.interactors.overview

import ba.grbo.core.domain.dm.Place
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RefreshPlace(
    private val fetchPlace: FetchPlace,
    private val updatePlace: UpdatePlace,
    private val fetchWeatherAndAirPollution: FetchWeatherAndAirPollution,
    private val dispatcher: CoroutineDispatcher
) {
    // Using flow to emit exception, because we're handling exceptions in the view model
    operator fun invoke(id: Long) = flow {
        try {
            val place = fetchPlace(id)
            invoke(place)
        } catch (e: Exception) {
            emit(e)
        }
    }.flowOn(dispatcher)

    suspend operator fun invoke(place: Place) {
        updatePlace(place.copy(weather = place.weather.copy(stale = true)))
        val (weather, airPollution) = fetchWeatherAndAirPollution(place.coordinates)
        updatePlace(place.copy(weather = weather.await(), airPollution = airPollution.await()))
    }
}