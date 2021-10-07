package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.R

@Composable
fun Visibility(text: String) {
    TypicalWeatherAttribute(
        imageId = R.drawable.ic_visibility,
        imageDescription = R.string.visibility_description,
        text = text
    )
}

@Preview(name = "10.0km")
@Preview(
    name = "10.0km",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun Visibility10Preview() {
    Preview {
        Visibility(text = "10.0km")
    }
}

@Preview(name = "7.5km")
@Preview(
    name = "7.5km",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun Visibility7_5Preview() {
    Preview {
        Visibility(text = "7.5km")
    }
}