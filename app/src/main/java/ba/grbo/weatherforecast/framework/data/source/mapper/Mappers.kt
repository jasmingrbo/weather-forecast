package ba.grbo.weatherforecast.framework.data.source.mapper

import ba.grbo.core.domain.dm.Alert
import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.domain.dm.Forecast
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.core.domain.dm.Place
import ba.grbo.core.domain.dm.BasicWeather
import ba.grbo.core.domain.dm.Weather
import ba.grbo.weatherforecast.framework.data.dto.local.CacheBasicPlace
import ba.grbo.weatherforecast.framework.data.dto.local.CachedPlace
import ba.grbo.weatherforecast.framework.mics.Country
import javax.inject.Inject

class Mappers @Inject constructor(private val adapters: JsonAdapters) {
    val first = object : JsonMapper<Place, CachedPlace> {
        override fun toDomain(dto: CachedPlace) = Place(
            id = dto.id,
            coordinates = adapters.coordinates.fromJson(dto.coordinates)!!,
            name = dto.name,
            address = dto.address,
            countryFlagResource = Country.getFlagResource(dto.countryCode),
            weather = Weather(
                current = adapters.weather.current.fromJson(dto.currentWeather)!!,
                forecast = Forecast(
                    hourly = adapters.weather.forecast.hourly.fromJson(dto.hourlyForecast)!!,
                    daily = adapters.weather.forecast.daily.fromJson(dto.dailyForecast)!!
                ),
                alert = adapters.weather.alert.fromJson(dto.weatherAlert)!!,
                stale = dto.weatherDataStale
            ),
            airPollution = adapters.airPollution.fromJson(dto.airPollution)!!,
            refreshFailed = dto.refreshFailed
        )

        override fun toData(dm: Place) = CachedPlace(
            id = dm.id,
            coordinates = adapters.coordinates.toJson(dm.coordinates),
            name = dm.name,
            address = dm.address,
            countryCode = Country.getCode(dm.countryFlagResource),
            currentWeather = adapters.weather.current.toJson(dm.weather.current),
            hourlyForecast = adapters.weather.forecast.hourly.toJson(dm.weather.forecast.hourly),
            dailyForecast = adapters.weather.forecast.daily.toJson(dm.weather.forecast.daily),
            weatherAlert = adapters.weather.alert.toJson(dm.weather.alert),
            airPollution = adapters.airPollution.toJson(dm.airPollution),
            weatherDataStale = dm.weather.stale,
            refreshFailed = dm.refreshFailed
        )
    }

    val second = object : JsonMapper<BasicPlace, CacheBasicPlace> {
        override fun toDomain(dto: CacheBasicPlace) = BasicPlace(
            id = dto.id,
            coordinates = adapters.coordinates.fromJson(dto.coordinates)!!,
            name = dto.name,
            countryFlagResource = Country.getFlagResource(dto.countryCode),
            weather = BasicWeather(
                current = adapters.weather.current.fromJson(dto.currentWeather)!!.simplify(),
                hasAlert = adapters.weather.alert.fromJson(dto.weatherAlert)!!.date != Alert.DEFAULT.date,
                stale = dto.weatherDataStale
            ),
            airQualityIndex = adapters.airPollution.fromJson(dto.airPollution)!!.airQualityIndex
        )

        override fun toData(dm: BasicPlace) = CacheBasicPlace.DEFAULT
    }

    val third = object : JsonMapper<Coordinates, String> {
        override fun toDomain(dto: String) = adapters.coordinates.fromJson(dto)!!

        override fun toData(dm: Coordinates) = adapters.coordinates.toJson(dm)
    }
}