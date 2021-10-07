package ba.grbo.core.interactors.overview

import ba.grbo.core.data.repositories.AirPollutionRepository
import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.interactors.overview.exceptions.FetchAirPollutionException

class FetchAirPollution(private val repository: AirPollutionRepository) {
    suspend operator fun invoke(coordinates: Coordinates) = try {
        repository.getAirPollution(coordinates.latitude, coordinates.longitude)
    } catch (e: Exception) {
        throw FetchAirPollutionException(coordinates, e)
    }
}