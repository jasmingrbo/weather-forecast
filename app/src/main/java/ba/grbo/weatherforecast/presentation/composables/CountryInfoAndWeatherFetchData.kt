package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.mics.roundedShimmeringPlaceholder

@Composable
fun CountryInfoAndWeatherFetchDate(
    @DrawableRes countryFlag: Int,
    placeName: String,
    fetchDate: String,
    loading: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CountryInfo(
            flag = countryFlag,
            placeName = placeName
        )

        // Weather fetch date
        Text(
            text = fetchDate,
            modifier = Modifier
                .align(Alignment.Top)
                .roundedShimmeringPlaceholder(visible = loading),
            style = MaterialTheme.typography.h6.copy(fontSize = 12.sp)
        )
    }
}

@Preview(name = "SarajevoLoading")
@Preview(
    name = "SarajevoLoading",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun CountryInfoAndWeatherFetchDateSarajevoLoadingPreview() {
    Preview {
        CountryInfoAndWeatherFetchDate(
            countryFlag = PreviewData.Place.SarajevoClearSky.countryFlagResource,
            placeName = PreviewData.Place.SarajevoClearSky.name,
            fetchDate = PreviewData.Place.SarajevoClearSky.weather.current.date,
            loading = true
        )
    }
}

@Preview(name = "SarajevoLoaded")
@Preview(
    name = "SarajevoLoaded",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun CountryInfoAndWeatherFetchDateSarajevoLoadedPreview() {
    Preview {
        CountryInfoAndWeatherFetchDate(
            countryFlag = PreviewData.Place.SarajevoClearSky.countryFlagResource,
            placeName = PreviewData.Place.SarajevoClearSky.name,
            fetchDate = PreviewData.Place.SarajevoClearSky.weather.current.date,
            loading = false
        )
    }
}

@Preview(name = "AachenLoaded")
@Preview(
    name = "AachenLoaded",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun CountryInfoAndWeatherFetchDateAachenLoadedPreview() {
    Preview {
        CountryInfoAndWeatherFetchDate(
            countryFlag = PreviewData.Place.AachenBrokenClouds.countryFlagResource,
            placeName = PreviewData.Place.AachenBrokenClouds.name,
            fetchDate = PreviewData.Place.AachenBrokenClouds.weather.current.date,
            loading = false
        )
    }
}