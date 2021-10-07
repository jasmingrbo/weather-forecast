package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.PlaceRepository
import ba.grbo.core.domain.dm.Location
import ba.grbo.core.domain.dm.Place
import ba.grbo.core.domain.dm.Result.Loading
import ba.grbo.core.domain.dm.Result.SourceResult.Failure
import ba.grbo.core.interactors.overview.exceptions.InsertPlaceException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InsertPlace(
    private val repository: PlaceRepository,
    private val fetchLatestPlace: FetchLatestPlace,
    private val updatePlace: UpdatePlace,
    private val fetchWeatherAndAirPollution: FetchWeatherAndAirPollution,
    private val dispatcher: CoroutineDispatcher,
) {
    operator fun invoke(location: Location) = flow {
        try {
            if (!repository.insertPlace(Place.createDefault(location))) {
                throw InsertPlaceException(location)
            }

            val (weather, airPollution) = fetchWeatherAndAirPollution(location.coordinates)

            // We're fetching the latest place because of its id that's been updated and is
            // different than the default one of Place.createDefault(...)
            updatePlace(
                place = fetchLatestPlace().copy(
                    weather = weather.await(),
                    airPollution = airPollution.await()
                )
            )
        } catch (e: Exception) {
            emit(Failure(if (e is InsertPlaceException) e else InsertPlaceException(location, e)))
        } finally {
            emit(Loading(false))
        }
    }.flowOn(dispatcher)

    operator fun invoke(place: Place) = flow {
        try {
            if (!repository.insertPlace(place)) throw InsertPlaceException(place)
        } catch (e: Exception) {
            emit(if (e is InsertPlaceException) e else InsertPlaceException(place, e))
        }
    }.flowOn(dispatcher)
}