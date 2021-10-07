package ba.grbo.weatherforecast.framework.data.source.remote.openweather

import ba.grbo.weatherforecast.BuildConfig.OPEN_WEATHER_API_KEY
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.NetworkAirPollution
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.NetworkWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("data/2.5/onecall?$API_KEY=$OPEN_WEATHER_API_KEY&exclude=minutely")
    suspend fun getNetworkWeather(
        @Query(LATITUDE) latitude: String,
        @Query(LONGITUDE) longitude: String,
        @Query(UNITS) units: String = "metric",
        @Query(LANGUAGE) language: String = "en",
    ): NetworkWeather

    @GET("/data/2.5/air_pollution?$API_KEY=$OPEN_WEATHER_API_KEY")
    suspend fun getNetworkAirPollution(
        @Query(LATITUDE) latitude: String,
        @Query(LONGITUDE) longitude: String
    ): NetworkAirPollution

    companion object {
        const val BASE_URL = "https://api.openweathermap.org"

        // Query values are defined by the service itself
        private const val API_KEY = "appid"
        private const val LATITUDE = "lat"
        private const val LONGITUDE = "lon"
        private const val UNITS = "units"
        private const val LANGUAGE = "lang"
    }
}