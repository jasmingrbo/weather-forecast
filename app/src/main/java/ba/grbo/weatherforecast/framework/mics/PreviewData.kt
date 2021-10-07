package ba.grbo.weatherforecast.framework.mics

import androidx.compose.ui.text.input.TextFieldValue
import ba.grbo.core.domain.DEFAULT
import ba.grbo.core.domain.dm.AirPollution
import ba.grbo.core.domain.dm.AirPollution.AirComponents
import ba.grbo.core.domain.dm.Alert
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.core.domain.dm.BasicWeather
import ba.grbo.core.domain.dm.Coordinates
import ba.grbo.core.domain.dm.CurrentWeather
import ba.grbo.core.domain.dm.DailyForecast
import ba.grbo.core.domain.dm.Date
import ba.grbo.core.domain.dm.Forecast
import ba.grbo.core.domain.dm.HourlyForecast
import ba.grbo.core.domain.dm.Location
import ba.grbo.core.domain.dm.Place
import ba.grbo.core.domain.dm.Precipitation
import ba.grbo.core.domain.dm.Temperature
import ba.grbo.core.domain.dm.TemperatureRange
import ba.grbo.core.domain.dm.Weather
import ba.grbo.core.domain.dm.Wind
import ba.grbo.weatherforecast.R
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.OverviewAppBarState

// Object declarations are initialized when they’re first used.
// A singleton won't be initialized until it's called for the first time, in the production it
// will never happen, since this singleton is only ever being used for preview function, which are
// as well never called.
object PreviewData {
    // When done, add more states
    object OverviewScreenState {
        val NonEmptyUnfocusedEnabledWithInternet = OverviewScreenState(
            appBar = OverviewAppBarState.NonEmptyUnfocusedEnabled,
            body = OverviewBodyState.Default
        )

        val EmptyFocusedEnabledWithoutInternet = OverviewScreenState(
            appBar = OverviewAppBarState.EmptyFocusedEnabled,
            body = OverviewBodyState.Default
        )
    }

    object OverviewAppBarState {
        val NonEmptyUnfocusedEnabled = OverviewAppBarState(
            query = Query.Sarajevo,
            focused = false,
            enabled = true,
            unfocus = false,
            hideKeyboard = false
        )

        val NonEmptyUnfocusedDisabled = OverviewAppBarState(
            query = Query.Sarajevo,
            focused = false,
            enabled = false,
            unfocus = false,
            hideKeyboard = false
        )
        val EmptyFocusedEnabled = OverviewAppBarState(
            query = Query.Empty,
            focused = true,
            enabled = true,
            unfocus = false,
            hideKeyboard = false
        )
        val EmptyFocusedDisabled = OverviewAppBarState(
            query = Query.Empty,
            focused = true,
            enabled = false,
            unfocus = false,
            hideKeyboard = false
        )
    }

    object OverviewBodyState {
        val Default =
            ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.OverviewBodyState.Default
    }

    object Place {
        val SarajevoClearSky = Place(
            id = 0,
            locatable = Location.SarajevoBH,
            weather = Weather(
                current = CurrentWeather(
                    "October 21, 11:34",
                    temperature = Temperature(measured = 19, feelsLike = 18),
                    pressure = 1028,
                    humidity = 80,
                    dewPoint = 4,
                    uvi = 1.5,
                    visibility = 7500,
                    wind = Wind(speed = 2.3, degrees = 360.0),
                    precipitation = Precipitation.DEFAULT,
                    description = "Clear sky",
                    "01d"
                ),
                forecast = Forecast(
                    hourly = List(48) {
                        HourlyForecast(
                            time = "11:00",
                            temperature = 18,
                            precipitationProbability = 1.4,
                            weatherIcon = "01d"
                        )
                    },
                    daily = List(8) {
                        DailyForecast(
                            date = "October 21, 12:00",
                            temperature = TemperatureRange(minimum = 13, maximum = 20),
                            precipitationProbability = 2.0,
                            icon = "01d"
                        )
                    }
                ),
                alert = Alert(
                    sender = "Unknown sender",
                    event = "Unknown event",
                    date = Date(start = "October 21, 12:00", end = "October 21, 13:00"),
                    message = "Unknown message",
                    tag = "Unknown tag"
                ),
                stale = false
            ),
            airPollution = AirPollution(
                airQualityIndex = 3,
                components = AirComponents(
                    carbonMonoxide = 1.0,
                    nitrogenMonoxide = 1.2,
                    nitrogenDioxide = 2.1,
                    ozone = 0.5,
                    sulphurDioxide = 3.3,
                    fineParticles = 1.1,
                    coarseParticles = 3.1,
                    ammonia = 1.2
                )
            ),
            refreshFailed = false
        )

        val AachenBrokenClouds = Place(
            id = 1,
            locatable = Location.Aachen,
            weather = Weather(
                current = CurrentWeather(
                    "October 22, 10:15",
                    temperature = Temperature(measured = 15, feelsLike = 16),
                    pressure = 1020,
                    humidity = 100,
                    dewPoint = 3,
                    uvi = 0.5,
                    visibility = 10000,
                    wind = Wind(speed = 0.3, degrees = 75.0),
                    precipitation = Precipitation.DEFAULT,
                    description = "Broken clouds",
                    "01d"
                ),
                forecast = Forecast(
                    hourly = List(48) {
                        HourlyForecast(
                            time = "12:00",
                            temperature = 16,
                            precipitationProbability = 2.4,
                            weatherIcon = "01d"
                        )
                    },
                    daily = List(8) {
                        DailyForecast(
                            date = "October 22, 12:01",
                            temperature = TemperatureRange(minimum = 12, maximum = 18),
                            precipitationProbability = 1.0,
                            icon = "01d"
                        )
                    }
                ),
                alert = Alert.DEFAULT,
                stale = false
            ),
            airPollution = AirPollution(
                airQualityIndex = 3,
                components = AirComponents(
                    carbonMonoxide = 1.5,
                    nitrogenMonoxide = 2.2,
                    nitrogenDioxide = 2.9,
                    ozone = 1.5,
                    sulphurDioxide = 2.6,
                    fineParticles = 1.4,
                    coarseParticles = 2.1,
                    ammonia = 0.5
                )
            ),
            refreshFailed = false
        )
    }

    object Places {


        val SarajevoAndAachen = listOf(
            Place.SarajevoClearSky.toBasic(),
            Place.AachenBrokenClouds.toBasic()
        )

        val Overflow = listOf(
            Place.SarajevoClearSky.toBasic(),
            Place.AachenBrokenClouds.toBasic(),
            Place.SarajevoClearSky.copy(
                coordinates = Coordinates("2", "2"),
                weather = Place.SarajevoClearSky.weather.copy(stale = true)
            ).toBasic(),
            Place.SarajevoClearSky.copy(
                coordinates = Coordinates("3", "3"),
                weather = Place.SarajevoClearSky.weather.copy(alert = Alert.DEFAULT)
            ).toBasic(),
            Place.AachenBrokenClouds.copy(
                coordinates = Coordinates("4", "4"),
                weather = Place.AachenBrokenClouds.weather.copy(stale = true)
            ).toBasic(),
            Place.SarajevoClearSky.copy(
                coordinates = Coordinates("5", "5"),
                weather = Place.AachenBrokenClouds.weather
            ).toBasic()
        )
    }

    object Query {
        val Sarajevo = TextFieldValue("Sarajevo")
        val Aachen = TextFieldValue("Aachen")
        val Empty = TextFieldValue(String.DEFAULT)
    }

    object Queries {
        val SarajevoAndAachen = listOf(Query.Sarajevo, Query.Aachen)
    }

    object Location {
        val SarajevoBH = Location(
            coordinates = Coordinates("0", "0"),
            name = "Sarajevo",
            address = "Federation of Bosnia and Herzegovina, 71000, Bosnia and Herzegovina",
            countryFlagResource = R.drawable.ic_bosnia_and_herzegovina
        )

        val SarajevoM = Location(
            coordinates = Coordinates("1", "2"),
            name = "Sarajevo",
            address = "Opasanica, Montenegro",
            countryFlagResource = R.drawable.ic_montenegro
        )

        val Aachen = Location(
            coordinates = Coordinates("1", "1"),
            name = "Aachen",
            address = "North Rhine-Westphalia, Germany",
            countryFlagResource = R.drawable.ic_germany
        )
    }

    object Locations {
        val Sarajevo = listOf(Location.SarajevoBH, Location.SarajevoM)
    }

    fun ba.grbo.core.domain.dm.Place.toBasic() = BasicPlace(
        id = id,
        coordinates = coordinates,
        name = name,
        countryFlagResource = countryFlagResource,
        weather = BasicWeather(
            current = weather.current.simplify(),
            hasAlert = weather.alert.date != Date.DEFAULT,
            stale = weather.stale
        ),
        airQualityIndex = airPollution.airQualityIndex
    )
}