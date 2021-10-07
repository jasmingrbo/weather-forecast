package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.R

@Composable
fun Humidity(text: String) {
    TypicalWeatherAttribute(
        imageId = R.drawable.ic_humidity,
        imageDescription = R.string.humidity_description,
        text = text,
        spacer = 2.dp
    )
}

@Preview(name = "25%")
@Preview(
    name = "25%",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun Humidity25Preview() {
    Preview {
        Humidity(text = "25%")
    }
}

@Preview(name = "80%")
@Preview(
    name = "80%",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun Humidity80Preview() {
    Preview {
        Humidity(text = "80%")
    }
}