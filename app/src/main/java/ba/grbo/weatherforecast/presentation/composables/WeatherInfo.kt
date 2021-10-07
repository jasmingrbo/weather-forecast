package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.R
import ba.grbo.weatherforecast.framework.mics.roundedShimmeringPlaceholder

@Composable
fun WeatherInfo(
    temperature: Int,
    loading: Boolean,
    icon: String,
    description: String,
    alerts: Boolean,
    airQuality: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // current temperature
        Text(
            text = "$temperature°C",
            modifier = Modifier.roundedShimmeringPlaceholder(visible = loading),
            style = MaterialTheme.typography.h2
        )

        HorizontalSpace(space = if ("01" !in icon || loading) 6.dp else 0.dp)

        Row(
            modifier = Modifier.roundedShimmeringPlaceholder(visible = loading),
            verticalAlignment = Alignment.CenterVertically
        ) {
            WeatherIconAndDescription(
                icon = icon,
                description = description
            )

            HorizontalSpace(space = 6.dp)

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (alerts) AlertImage()
                AirQualityIndex(airQuality = airQuality)
            }
        }
    }
}

@Preview(name = "Loading")
@Preview(
    name = "Loading",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun WeatherInfoLoadingPreview() {
    Preview {
        WeatherInfo(
            temperature = 20,
            loading = true,
            icon = "01n",
            description = "Sunny",
            alerts = true,
            airQuality = R.drawable.ic_aq_poor
        )
    }
}

@Preview(name = "Loaded")
@Preview(
    name = "Loaded",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun WeatherInfoLoadedPreview() {
    Preview {
        WeatherInfo(
            temperature = 20,
            loading = false,
            icon = "01n",
            description = "Sunny",
            alerts = true,
            airQuality = R.drawable.ic_aq_poor
        )
    }
}

@Preview(name = "LoadedLongDescription")
@Preview(
    name = "LoadedLongDescription",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun WeatherInfoLoadedLongDescriptionPreview() {
    Preview {
        WeatherInfo(
            temperature = 20,
            loading = false,
            icon = "01n",
            description = "Thunderstorm with heavy rain",
            alerts = true,
            airQuality = R.drawable.ic_aq_poor
        )
    }
}