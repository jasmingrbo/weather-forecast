package ba.grbo.weatherforecast.presentation.composables.bodies

import androidx.compose.runtime.Composable
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent
import ba.grbo.weatherforecast.framework.data.CommonBodyState
import ba.grbo.weatherforecast.presentation.composables.AppBar

@Composable
fun CommonBody(
    state: CommonBodyState,
    onEvent: (CommonBodyEvent) -> Unit
) {
    AppBar(
        state = state.appBarState,
        onEvent = onEvent
    )
    // InternetAvailabilityBanner()
}

// Add previews