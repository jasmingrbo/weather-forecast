package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.R

@Composable
fun TypicalWeatherAttribute(
    @DrawableRes imageId: Int,
    @StringRes imageDescription: Int,
    text: String,
    spacer: Dp = 5.dp,
) {
    WeatherAttribute {
        TypicalWeatherAttributeContent(
            imageId = imageId,
            imageDescription = imageDescription,
            text = text,
            spacer = spacer
        )
    }
}

@Preview(name = "Visibility")
@Preview(
    name = "Visibility",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun TypicalWeatherAttributeVisibilityPreview() {
    Preview {
        TypicalWeatherAttribute(
            imageId = R.drawable.ic_visibility,
            imageDescription = R.string.visibility_description,
            text = "5.5km"
        )
    }
}

@Preview(name = "Humidity")
@Preview(
    name = "Humidity",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun TypicalWeatherAttributeHumidityPreview() {
    Preview {
        TypicalWeatherAttribute(
            imageId = R.drawable.ic_humidity,
            imageDescription = R.string.humidity_description,
            text = "35%"
        )
    }
}

@Preview(name = "Uvi")
@Preview(
    name = "Uvi",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun TypicalWeatherAttributeUviPreview() {
    Preview {
        TypicalWeatherAttribute(
            imageId = R.drawable.ic_uvi,
            imageDescription = R.string.uvi_description,
            text = "0.5"
        )
    }
}