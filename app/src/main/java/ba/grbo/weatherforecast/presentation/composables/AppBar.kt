package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.AppBarCallables
import ba.grbo.weatherforecast.AppBarCallables.DetailsAppBarCallables
import ba.grbo.weatherforecast.AppBarCallables.OverviewAppBarCallables
import ba.grbo.weatherforecast.AppBarCallables.SettingsAppBarCallables
import ba.grbo.weatherforecast.AppBarState
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalTransitionApi::class)
@Composable
fun AppBar(
    state: AppBarState,
    callables: AppBarCallables
) {
    Column {
        AppBarBody(state = state, callables = callables)
        Divider()
    }
}

@Composable
private fun AppBarBody(
    state: AppBarState,
    callables: AppBarCallables
) {
    when (state) {
        is AppBarState.Overview -> OverviewAppBarBody(state = state, callables = callables)
        is AppBarState.Details -> DetailsAppBarBody(state = state, callables = callables)
        is AppBarState.Settings -> SettingsAppBarBody(state = state, callables = callables)
    }
}

@Composable
private fun OverviewAppBarBody(
    state: AppBarState.Overview,
    callables: AppBarCallables
) {
    when (callables) {
        is OverviewAppBarCallables -> OverviewAppBarBody(
            state = state.value,
            callables = callables
        )
        is DetailsAppBarCallables,
        is SettingsAppBarCallables -> {
            throw IllegalArgumentException("Wrong callables: ${callables.javaClass.simpleName}")
        }
    }
}

@Composable
private fun DetailsAppBarBody(
    state: AppBarState.Details,
    callables: AppBarCallables
) {
    when (callables) {
        is DetailsAppBarCallables -> DetailsAppBarBody(state = state.value, callables = callables)
        is OverviewAppBarCallables,
        is SettingsAppBarCallables -> {
            throw IllegalArgumentException("Wrong callables: ${callables.javaClass.simpleName}")
        }
    }
}

@Composable
private fun SettingsAppBarBody(
    state: AppBarState.Settings,
    callables: AppBarCallables
) {
    when (callables) {
        is SettingsAppBarCallables -> SettingsAppBarBody(title = state.value, callables = callables)
        is DetailsAppBarCallables,
        is OverviewAppBarCallables -> {
            throw IllegalArgumentException("Wrong callables: ${callables.javaClass.simpleName}")
        }
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
                callables = PreviewData.overviewAppBarCallables
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
                callables = PreviewData.overviewAppBarCallables
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
                callables = PreviewData.overviewAppBarCallables
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
                callables = PreviewData.overviewAppBarCallables
            )
        }
    }
}