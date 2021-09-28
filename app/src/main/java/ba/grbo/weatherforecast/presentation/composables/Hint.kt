package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@Composable
fun Hint(isEnabled: Boolean) {
    Text(
        text = "Search for a place",
        style = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface.copy(
                if (isEnabled) ContentAlpha.medium else ContentAlpha.disabled
            )
        )
    )
}

@Preview(
    name = "Light-Enabled",
    showBackground = true
)
@Preview(
    name = "Dark-Enabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HintPreviewEnabled() {
    WeatherForecastTheme {
        Surface {
            Hint(isEnabled = true)
        }
    }
}

@Preview(
    name = "Light-Disabled",
    showBackground = true
)
@Preview(
    name = "Dark-Disabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HintPreviewDisabled() {
    WeatherForecastTheme {
        Surface {
            Hint(isEnabled = false)
        }
    }
}