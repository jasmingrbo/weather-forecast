package ba.grbo.weatherforecast.framework.data.source.mapper

import ba.grbo.core.domain.dm.AirPollution
import ba.grbo.core.domain.dm.Alert
import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.domain.dm.CurrentWeather
import ba.grbo.core.domain.dm.DailyForecast
import ba.grbo.core.domain.dm.HourlyForecast
import ba.grbo.weatherforecast.framework.di.AirPollutionJsonAdapter
import ba.grbo.weatherforecast.framework.di.AlertsJsonAdapter
import ba.grbo.weatherforecast.framework.di.CoordinatesJsonAdapter
import ba.grbo.weatherforecast.framework.di.CurrentWeatherJsonAdapter
import ba.grbo.weatherforecast.framework.di.DailyForecastsJsonAdapter
import ba.grbo.weatherforecast.framework.di.HourlyForecastsJsonAdapter
import com.squareup.moshi.JsonAdapter
import javax.inject.Inject

class JsonAdapters @Inject constructor(
    @CoordinatesJsonAdapter val coordinates: JsonAdapter<Coordinates>,
    val weather: WeatherJsonAdapter,
    @AirPollutionJsonAdapter val airPollution: JsonAdapter<AirPollution>
)

class WeatherJsonAdapter @Inject constructor(
    @CurrentWeatherJsonAdapter val current: JsonAdapter<CurrentWeather>,
    val forecast: ForecastJsonAdapter,
    @AlertsJsonAdapter val alert: JsonAdapter<Alert>,
)

class ForecastJsonAdapter @Inject constructor(
    @HourlyForecastsJsonAdapter val hourly: JsonAdapter<List<HourlyForecast>>,
    @DailyForecastsJsonAdapter val daily: JsonAdapter<List<DailyForecast>>,
)