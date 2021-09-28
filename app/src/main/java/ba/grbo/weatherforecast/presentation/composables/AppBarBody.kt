package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.runtime.Composable
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent
import ba.grbo.weatherforecast.framework.data.CommonBodyState

@Composable
fun AppBarBody(
    state: CommonBodyState.AppBarState,
    onEvent: (CommonBodyEvent) -> Unit
) {
    when (state) {
        is CommonBodyState.AppBarState.Overview -> OverviewAppBarBody(
            state = state.value,
            onEvent = onEvent
        )
        is CommonBodyState.AppBarState.Details -> DetailsAppBarBody(
            state = state.value,
            onEvent = onEvent
        )
        is CommonBodyState.AppBarState.Settings -> SettingsAppBarBody(
            title = state.value,
            onEvent = onEvent
        )
    }
}

// Add previews
