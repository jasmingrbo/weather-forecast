package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.mics.customFadeIn
import ba.grbo.weatherforecast.framework.mics.customFadeOut
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedHint(visible: Boolean, enabled: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = customFadeIn(150), // total has to be 300, 150 + 150 (resetQuery animation)
        exit = customFadeOut()
    ) {
        Hint(enabled = enabled)
    }
}

@Preview(
    name = "Light-VisibleEnabled",
    showBackground = true
)
@Preview(
    name = "Dark-VisibleEnabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintVisibleEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedHint(
                visible = true,
                enabled = true
            )
        }
    }
}

@Preview(
    name = "Light-VisibleDisabled",
    showBackground = true
)
@Preview(
    name = "Dark-VisibleDisabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintVisibleDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedHint(
                visible = true,
                enabled = false
            )
        }
    }
}

@Preview(
    name = "Light-InvisibleEnabled",
    showBackground = true
)
@Preview(
    name = "Dark-InvisibleEnabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintInvisibleEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedHint(
                visible = false,
                enabled = true
            )
        }
    }
}

@Preview(
    name = "Light-InvisibleDisabled",
    showBackground = true
)
@Preview(
    name = "Dark-InvisibleDisabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintInvisibleDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedHint(
                visible = false,
                enabled = false
            )
        }
    }
}