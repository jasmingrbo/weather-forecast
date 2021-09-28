package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@Composable
fun MagnifierIcon(
    modifier: Modifier = Modifier,
    enabled: Boolean,
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Default.Search,
        contentDescription = "Search",
        tint = MaterialTheme.colors.onSurface.copy(
            alpha = if (enabled) TextFieldDefaults.IconOpacity
            else ContentAlpha.disabled
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
private fun MagnifierIconEnabledPreview() {
    WeatherForecastTheme {
        MagnifierIcon(enabled = true)
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
private fun MagnifierIconDisabledPreview() {
    WeatherForecastTheme {
        MagnifierIcon(enabled = false)
    }
}