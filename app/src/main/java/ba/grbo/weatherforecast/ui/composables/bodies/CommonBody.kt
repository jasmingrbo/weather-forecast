package ba.grbo.weatherforecast.ui.composables.bodies

import androidx.compose.runtime.Composable
import ba.grbo.weatherforecast.AppBarCallables
import ba.grbo.weatherforecast.CommonBodyState
import ba.grbo.weatherforecast.ui.composables.AppBar

@Composable
fun CommonBody(
    state: CommonBodyState,
    callables: AppBarCallables
) {
    AppBar(
        state = state.appBarState,
        callables = callables
    )
    // InternetAvailabilityBanner()
}