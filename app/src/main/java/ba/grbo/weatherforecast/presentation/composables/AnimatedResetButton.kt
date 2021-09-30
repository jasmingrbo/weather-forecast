package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedResetButton(
    modifier: Modifier = Modifier,
    visible: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ResetButton(
            modifier = modifier,
            enabled = enabled,
            onClick = onClick
        )
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
private fun AnimatedResetButtonVisibleEnabledPreview() {
    WeatherForecastTheme {
        AnimatedResetButton(
            visible = true,
            enabled = true,
            onClick = {}
        )
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
private fun AnimatedResetButtonVisibleDisabledPreview() {
    WeatherForecastTheme {
        AnimatedResetButton(
            visible = true,
            enabled = false,
            onClick = {}
        )
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
private fun AnimatedResetButtonInvisibleEnabledPreview() {
    WeatherForecastTheme {
        AnimatedResetButton(
            visible = false,
            enabled = true,
            onClick = {}
        )
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
private fun AnimatedResetButtonInvisibleDisabledPreview() {
    WeatherForecastTheme {
        AnimatedResetButton(
            visible = false,
            enabled = false,
            onClick = {}
        )
    }
}