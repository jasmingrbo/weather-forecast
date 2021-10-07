package ba.grbo.weatherforecast.framework.data.source.remote.locationiq

import ba.grbo.weatherforecast.BuildConfig.LOCATION_IQ_API_KEY
import ba.grbo.weatherforecast.framework.data.dto.remote.locationiq.NetworkLocation
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationIQService {

    @GET("v1/autocomplete.php?$API_KEY=$LOCATION_IQ_API_KEY")
    suspend fun getNetworkLocations(
        @Query(QUERY) query: String,
        @Query(LIMIT) limit: String = "10",
        @Query(COUNTRY_CODES) countryCodes: String = "",
        @Query(ACCEPT_LANGUAGE) acceptLanguage: String = "en",
        @Query(TAG) tag: String = "place:*",
        @Query(DEDUPE) dedupe: String = "1",
        @Query(IMPORTANCE_SORT) importanceSort: String = "1",
    ): List<NetworkLocation>

    companion object {
        const val BASE_URL = "https://api.locationiq.com"
        const val QUERY_LIMIT = 200 // The limit is imposed by the LocationIQ API

        // Query values are defined by the service itself
        private const val API_KEY = "key"
        private const val QUERY = "q"
        private const val LIMIT = "limit"
        private const val COUNTRY_CODES = "country_codes"
        private const val ACCEPT_LANGUAGE = "accept-language"
        private const val TAG = "tag"
        private const val DEDUPE = "dedupe"
        private const val IMPORTANCE_SORT = "importancesort"
    }
}