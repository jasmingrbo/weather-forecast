package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.mics.customFadeIn
import ba.grbo.weatherforecast.framework.mics.customFadeOut
import ba.grbo.weatherforecast.framework.mics.isEmpty
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedHint(query: TextFieldValue, enabled: Boolean) {
    AnimatedVisibility(
        visible = query.isEmpty(),
        enter = customFadeIn(150), // total has to be 300, 150 + 150 (resetQuery animation)
        exit = customFadeOut()
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
                query = PreviewData.Query.NonEmpty,
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
                query = PreviewData.Query.NonEmpty,
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
                query = PreviewData.Query.Empty,
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
                query = PreviewData.Query.Empty,
                enabled = false
            )
        }
    }
}