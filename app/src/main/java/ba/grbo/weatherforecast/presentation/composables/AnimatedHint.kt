package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedHint(query: String, enabled: Boolean) {
    AnimatedVisibility(
        visible = query.isEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Hint(enabled = enabled)
    }
}

@Preview(
    name = "Light-NonEmptyEnabled",
    showBackground = true
)
@Preview(
    name = "Dark-NonEmptyEnabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintNonEmptyEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedHint(
                query = "Sarajevo",
                enabled = true
            )
        }
    }
}

@Preview(
    name = "Light-NonEmptyDisabled",
    showBackground = true
)
@Preview(
    name = "Dark-NonEmptyDisabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintNonEmptyDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedHint(
                query = "Sarajevo",
                enabled = false
            )
        }
    }
}

@Preview(
    name = "Light-EmptyEnabled",
    showBackground = true
)
@Preview(
    name = "Dark-EmptyEnabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintEmptyEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedHint(
                query = "",
                enabled = true
            )
        }
    }
}

@Preview(
    name = "Light-EmptyDisabled",
    showBackground = true
)
@Preview(
    name = "Dark-EmptyDisabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintEmptyDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedHint(
                query = "",
                enabled = false
            )
        }
    }
}