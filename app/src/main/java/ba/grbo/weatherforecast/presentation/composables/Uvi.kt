package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.R

@Composable
fun Uvi(text: String) {
    TypicalWeatherAttribute(
        imageId = R.drawable.ic_uvi,
        imageDescription = R.string.uvi_description,
        text = text
    )
}

@Preview(name = "0.0")
@Preview(
    name = "0.0",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun Uvi0_0Preview() {
    Preview {
        Uvi(text = "0.0")
    }
}

@Preview(name = "1.5")
@Preview(
    name = "1.5",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun Uvi1_5Preview() {
    Preview {
        Uvi(text = "1.5")
    }
}