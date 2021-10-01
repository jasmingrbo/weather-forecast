package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent
import ba.grbo.weatherforecast.framework.data.OverviewScreenState.OverviewAppBarState
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@Composable
fun OverviewAppBar(
    state: OverviewAppBarState,
    onEvent: (OverviewScreenEvent) -> Unit,
) {
    AppBar(
        body = {
            OverviewAppBarBody(
                state = state,
                onEvent = onEvent
            )
        }
    )
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
            OverviewAppBar(
                state = PreviewData.OverviewAppBarState.NonEmptyUnfocusedEnabled,
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
            OverviewAppBar(
                state = PreviewData.OverviewAppBarState.NonEmptyUnfocusedDisabled,
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
            OverviewAppBar(
                state = PreviewData.OverviewAppBarState.EmptyFocusedEnabled,
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
            OverviewAppBar(
                state = PreviewData.OverviewAppBarState.EmptyFocusedDisabled,
                onEvent = {}
            )
        }
    }
}