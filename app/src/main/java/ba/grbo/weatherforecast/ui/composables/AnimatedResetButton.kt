package ba.grbo.weatherforecast.ui.composables

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.ui.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedResetButton(
    modifier: Modifier = Modifier,
    query: String,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = query.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ResetButton(
            modifier = modifier,
            isEnabled = isEnabled,
            onClick = onClick
        )
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
private fun AnimatedResetButtonNonEmptyEnabledPreview() {
    WeatherForecastTheme {
        AnimatedResetButton(
            query = "Sarajevo",
            isEnabled = true,
            onClick = {}
        )
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
private fun AnimatedResetButtonNonEmptyDisabledPreview() {
    WeatherForecastTheme {
        AnimatedResetButton(
            query = "Sarajevo",
            isEnabled = false,
            onClick = {}
        )
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
private fun AnimatedResetButtonEmptyEnabledPreview() {
    WeatherForecastTheme {
        AnimatedResetButton(
            query = "",
            isEnabled = true,
            onClick = {}
        )
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
private fun AnimatedResetButtonEmptyDisabledPreview() {
    WeatherForecastTheme {
        AnimatedResetButton(
            query = "",
            isEnabled = false,
            onClick = {}
        )
    }
}