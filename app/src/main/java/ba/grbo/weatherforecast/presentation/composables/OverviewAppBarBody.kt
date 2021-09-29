package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.createChildTransition
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.framework.data.Body
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent
import ba.grbo.weatherforecast.framework.data.CommonBodyState
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalTransitionApi::class)
@Composable
fun OverviewAppBarBody(
    state: CommonBodyState.AppBarState.Overview.OverviewAppBarState,
    onEvent: (CommonBodyEvent) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val transition = updateTransition(
            targetState = state.focused,
            label = "OverviewAppBarBody-Transition"
        )

        val focusManager = LocalFocusManager.current
        LaunchedEffect(key1 = state.unfocus) { if (state.unfocus) focusManager.clearFocus(true) }

        LocationSearcher(
            modifier = Modifier.weight(1f),
            query = state.query,
            enabled = state.enabled,
            hideKeyboard = state.hideKeyboard,
            focusedTransition = transition.createChildTransition { focused -> focused },
            onQueryChange = { query -> onEvent(CommonBodyEvent.OnQueryChange(query)) },
            onFocusChanged = { focused -> onEvent(CommonBodyEvent.OnFocusChanged(focused)) },
            onUpButtonClick = { onEvent(CommonBodyEvent.OnUpButtonClick(Body.OVERVIEW)) },
            onResetButtonClick = { onEvent(CommonBodyEvent.OnResetButtonClick) },
            onDoneImeActionPressed = { onEvent(CommonBodyEvent.OnDoneImeActionPressed) },
            onSoftwareKeyboardHidden = { onEvent(CommonBodyEvent.OnSoftwareKeyboardHidden) }
        )
        AnimatedOverflowButton(
            modifier = Modifier.padding(end = 2.dp),
            locationSearcherFocusedTransition = transition.createChildTransition { focused ->
                focused
            },
            onClick = { onEvent(CommonBodyEvent.OnOverflowButtonClick) }
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
                state = PreviewData.OverviewAppBarState.NonEmptyUnfocusedEnabled.value,
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
private fun OverviewAppBarBodyNonEmptyUnfocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewAppBarBody(
                state = PreviewData.OverviewAppBarState.NonEmptyUnfocusedDisabled.value,
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
private fun OverviewAppBarBodyEmptyFocusedEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewAppBarBody(
                state = PreviewData.OverviewAppBarState.EmptyFocusedEnabled.value,
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
private fun OverviewAppBarBodyEmptyFocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewAppBarBody(
                state = PreviewData.OverviewAppBarState.EmptyFocusedDisabled.value,
                onEvent = {}
            )
        }
    }
}