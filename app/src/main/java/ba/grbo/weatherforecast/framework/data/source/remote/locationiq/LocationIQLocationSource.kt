package ba.grbo.weatherforecast.framework.data.source.remote.locationiq

import ba.grbo.core.data.LocationSource
import ba.grbo.weatherforecast.framework.mics.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationIQLocationSource @Inject constructor(
    private val locationIQService: LocationIQService,
    private val dispatcher: CoroutineDispatcher,
) : LocationSource {
    override suspend fun get(query: String) = withContext(dispatcher) {
        locationIQService.getNetworkLocations(query = query).toDomain()
    }
}