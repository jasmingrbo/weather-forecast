package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent
import ba.grbo.weatherforecast.framework.data.CommonBodyState
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalTransitionApi::class)
@Composable
fun AppBar(
    state: CommonBodyState.AppBarState,
    onEvent: (CommonBodyEvent) -> Unit
) {
    Column {
        AppBarBody(state = state, onEvent = onEvent)
        Divider()
    }
}

@Preview(
    name = "Light-NonEmptyUnfocusedEnabled",
    showBackground = true
)
@Preview(
    name = "Dark-NonEmptyUnfocusedEnabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun OverviewAppBarNonEmptyUnfocusedEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            AppBar(
                state = PreviewData.overviewAppBarStates.nonEmptyUnfocusedEnabled,
                onEvent = {}
            )
        }
    }
}

@Preview(
    name = "Light-NonEmptyUnfocusedDisabled",
    showBackground = true
)
@Preview(
    name = "Dark-NonEmptyUnfocusedDisabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun OverviewAppBarNonEmptyUnfocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            AppBar(
                state = PreviewData.overviewAppBarStates.nonEmptyUnfocusedDisabled,
                onEvent = {}
            )
        }
    }
}

@Preview(
    name = "Light-EmptyFocusedEnabled",
    showBackground = true
)
@Preview(
    name = "Dark-EmptyFocusedEnabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun OverviewAppBarEmptyFocusedEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            AppBar(
                state = PreviewData.overviewAppBarStates.emptyFocusedEnabled,
                onEvent = {}
            )
        }
    }
}

@Preview(
    name = "Light-EmptyFocusedDisabled",
    showBackground = true
)
@Preview(
    name = "Dark-EmptyFocusedDisabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun OverviewAppBarEmptyFocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            AppBar(
                state = PreviewData.overviewAppBarStates.emptyFocusedDisabled,
                onEvent = {}
            )
        }
    }
}