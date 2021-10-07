package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.mics.roundedShimmeringPlaceholder

@Composable
fun WeatherAttributes(
    modifier: Modifier = Modifier,
    loading: Boolean,
    visibilityText: String,
    humidityText: String,
    uviText: String,
    windText: String,
    windWaneRotationAngle: Float,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .roundedShimmeringPlaceholder(visible = loading),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Visibility(text = visibilityText)
        Humidity(text = humidityText)
        Uvi(text = uviText)
        Wind(
            text = windText,
            rotationAngle = windWaneRotationAngle
        )
    }
}

@Preview(name = "Loading")
@Preview(
    name = "Loading",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun WeatherAttributesLoadingPreview() {
    Preview {
        WeatherAttributes(
            loading = true,
            visibilityText = "10.0km",
            humidityText = "35%",
            uviText = "0.2",
            windText = "1.2m/s SE", // randomly put SE, doesn't have to correspond to the actual angle
            windWaneRotationAngle = 15f
        )
    }
}

@Preview(name = "Loaded")
@Preview(
    name = "Loaded",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun WeatherAttributesLoadedPreview() {
    Preview {
        WeatherAttributes(
            loading = false,
            visibilityText = "10.0km",
            humidityText = "35%",
            uviText = "0.2",
            windText = "1.2m/s S",
            windWaneRotationAngle = 15f
        )
    }
}