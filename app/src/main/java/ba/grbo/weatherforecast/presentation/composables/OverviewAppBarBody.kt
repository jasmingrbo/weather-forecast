package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.createChildTransition
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.AppBarCallables.OverviewAppBarCallables
import ba.grbo.weatherforecast.OverviewAppBarState
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalTransitionApi::class)
@Composable
fun OverviewAppBarBody(
    state: OverviewAppBarState,
    callables: OverviewAppBarCallables
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val transition = updateTransition(
            targetState = state.isFocused,
            label = "OverviewAppBarBody-Transition"
        )
        LocationSearcher(
            modifier = Modifier.weight(1f),
            query = state.query,
            isEnabled = state.isEnabled,
            isFocusedTransition = transition.createChildTransition { isFocused -> isFocused },
            onQueryChange = callables.onQueryChange,
            onFocusChanged = callables.onFocusChange,
            onUpClick = callables.onUpClicked,
            onResetClick = callables.onResetClicked
        )
        AnimatedOverflowButton(
            modifier = Modifier.padding(end = 2.dp),
            isLocationSearcherFocusedTransition = transition.createChildTransition { isFocused ->
                isFocused
            },
            onClick = callables.onOverflowClicked
        )
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
private fun OverviewAppBarBodyNonEmptyUnfocusedEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewAppBarBody(
                state = PreviewData.overviewAppBarStates.nonEmptyUnfocusedEnabled.value,
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
private fun OverviewAppBarBodyNonEmptyUnfocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewAppBarBody(
                state = PreviewData.overviewAppBarStates.nonEmptyUnfocusedDisabled.value,
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
private fun OverviewAppBarBodyEmptyFocusedEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewAppBarBody(
                state = PreviewData.overviewAppBarStates.emptyFocusedEnabled.value,
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
private fun OverviewAppBarBodyEmptyFocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewAppBarBody(
                state = PreviewData.overviewAppBarStates.emptyFocusedDisabled.value,
                callables = PreviewData.overviewAppBarCallables
            )
        }
    }
}