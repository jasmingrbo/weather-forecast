package ba.grbo.weatherforecast.presentation.composables.screens.overview

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Uninitialized
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.mics.retrieveOrDefault
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme
import ba.grbo.weatherforecast.presentation.composables.AppBar
import ba.grbo.weatherforecast.presentation.composables.Screen

@Composable
fun OverviewScreen(
    state: OverviewScreenState,
    onEvent: (OverviewScreenEvent) -> Unit,
    onPlaceClicked: (Long) -> Unit
) {
    Screen(
        appBar = {
            AppBar {
                OverviewScreenAppBarContent(
                    query = state.appBar.query,
                    focused = state.appBar.focused,
                    enabled = state.appBar.enabled,
                    unfocus = state.appBar.unfocus,
                    hideKeyboard = state.appBar.hideKeyboard,
                    onEvent = onEvent
                )
            }
        },
        body = {
            OverviewScreenBody(
                appBarQuery = state.appBar.query.text,
                appBarFocused = state.appBar.focused,
                loading = state.body.loading,
                internet = state.body.internet,
                places = state.body.places,
                queries = state.body.queries,
                locations = state.body.locations.retrieveOrDefault(
                    key = state.appBar.query.text,
                    default = Uninitialized
                ),
                onEvent = onEvent,
                onPlaceClicked = onPlaceClicked
            )
        }
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
                onEvent = {},
                onPlaceClicked = {}
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
                onEvent = {},
                onPlaceClicked = {}
            )
        }
    }
}