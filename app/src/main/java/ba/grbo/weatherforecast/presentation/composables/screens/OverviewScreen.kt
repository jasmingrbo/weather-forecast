package ba.grbo.weatherforecast.presentation.composables.screens

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent
import ba.grbo.weatherforecast.framework.data.OverviewScreenState
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme
import ba.grbo.weatherforecast.presentation.composables.OverviewAppBar
import ba.grbo.weatherforecast.presentation.composables.Screen

@Composable
fun OverviewScreen(
    state: OverviewScreenState,
    onEvent: (OverviewScreenEvent) -> Unit,
) {
    Screen(
        appBar = {
            OverviewAppBar(
                state = state.appBar,
                onEvent = onEvent
            )
        },
        body = {}
    )
}

@Preview(
    name = "Light-NonEmptyUnfocusedEnabledWithInternet",
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Dark-NonEmptyUnfocusedEnabledWithInternet",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
private fun OverviewScreenNonEmptyUnfocusedEnabledWithInternetPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewScreen(
                state = PreviewData.OverviewScreenState.NonEmptyUnfocusedEnabledWithInternet,
                onEvent = {}
            )
        }
    }
}

@Preview(
    name = "Light-EmptyFocusedEnabledWithoutInternet",
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Dark-EmptyFocusedEnabledWithoutInternet",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
private fun OverviewScreenEmptyFocusedEnabledWithoutInternetPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewScreen(
                state = PreviewData.OverviewScreenState.EmptyFocusedEnabledWithoutInternet,
                onEvent = {}
            )
        }
    }
}