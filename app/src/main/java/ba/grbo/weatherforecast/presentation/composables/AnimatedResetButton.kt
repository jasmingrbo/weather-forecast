package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.mics.isNotEmpty
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedResetButton(
    modifier: Modifier = Modifier,
    query: TextFieldValue,
    enabled: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = query.isNotEmpty(),
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
            query = PreviewData.Query.NonEmpty,
            enabled = true,
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
            query = PreviewData.Query.NonEmpty,
            enabled = false,
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
            query = PreviewData.Query.Empty,
            enabled = true,
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
            query = PreviewData.Query.Empty,
            enabled = false,
            onClick = {}
        )
    }
}