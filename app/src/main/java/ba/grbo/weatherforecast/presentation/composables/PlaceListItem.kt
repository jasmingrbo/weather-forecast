package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.mics.PreviewData.Place.AachenBrokenClouds
import ba.grbo.weatherforecast.framework.mics.PreviewData.toBasic
import ba.grbo.weatherforecast.framework.mics.airQualityIconResource
import ba.grbo.weatherforecast.framework.mics.format
import ba.grbo.weatherforecast.framework.mics.formatAsHumidity
import ba.grbo.weatherforecast.framework.mics.formatAsUvi
import ba.grbo.weatherforecast.framework.mics.formatAsVisibility

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlaceListItem(
    place: BasicPlace,
    clickable: Boolean,
    last: Boolean,
    onClick: (BasicPlace) -> Unit,
    onLongClick: (BasicPlace) -> Unit
) {
    val data = MutableLiveData(true)
    data.postValue(false)

    Card(
        modifier = Modifier.padding(
            start = 12.dp,
            end = 12.dp,
            top = 12.dp,
            bottom = if (last) 12.dp else 0.dp
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp
    ) {
        // We're setting clickable here and not on the card because, if set on the card, ripple
        // is not rounded, onClick on card fixes this, but there is no onLongClick on Card
        Column(
            modifier = Modifier
                .combinedClickable(
                    enabled = clickable,
                    onClick = { onClick(place) },
                    onLongClick = { onLongClick(place) }
                )
                .padding(12.dp)

        ) {
            CountryInfoAndWeatherFetchDate(
                countryFlag = place.countryFlagResource,
                placeName = place.name,
                fetchDate = place.weather.current.date,
                loading = place.weather.stale
            )

            VerticalSpace(space = 4.dp)

            WeatherInfo(
                temperature = place.weather.current.temperature,
                loading = place.weather.stale,
                icon = place.weather.current.icon,
                description = place.weather.current.description,
                alerts = place.weather.hasAlert,
                airQuality = place.airQualityIconResource
            )

            VerticalSpace(space = 4.dp)

            WeatherAttributes(
                loading = place.weather.stale,
                visibilityText = place.weather.current.visibility.formatAsVisibility(),
                humidityText = place.weather.current.humidity.formatAsHumidity(),
                uviText = place.weather.current.uvi.formatAsUvi(),
                windText = place.weather.current.wind.format(),
                windWaneRotationAngle = place.weather.current.wind.rotationAngle,
            )
        }
    }
}

@Preview(name = "SarajevoClearSkyLoading")
@Preview(
    name = "SarajevoClearSkyLoading",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PlaceListItemSarajevoClearSkyLoadingPreview() {
    Preview {
        PlaceListItem(
            place = PreviewData.Place.SarajevoClearSky.copy(
                weather = PreviewData.Place.SarajevoClearSky.weather.copy(
                    stale = true
                )
            ).toBasic(),
            clickable = true,
            last = true,
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview(name = "SarajevoClearSkyLoaded")
@Preview(
    name = "SarajevoClearSkyLoaded",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PlaceListItemSarajevoClearSkyLoadedPreview() {
    Preview {
        PlaceListItem(
            place = PreviewData.Place.SarajevoClearSky.toBasic(),
            clickable = true,
            last = true,
            onClick = {},
            onLongClick = {}
        )
    }
}

@Preview(name = "AachenBrokenClouds")
@Preview(
    name = "AachenBrokenClouds",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PlaceListItemAachenBrokenCloudsPreview() {
    Preview {
        PlaceListItem(
            place = AachenBrokenClouds.toBasic(),
            clickable = true,
            last = true,
            onClick = {},
            onLongClick = {}
        )
    }
}