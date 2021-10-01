package ba.grbo.weatherforecast.presentation.composables

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.createChildTransition
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.FocusChanged
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.OverflowButtonClicked
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.QueryChanged
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.ResetButtonClicked
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.UpButtonClicked
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.KeyboardEvent.DoneImeActionClicked
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.KeyboardEvent.KeyboardHidden
import ba.grbo.weatherforecast.framework.data.OverviewScreenState.OverviewAppBarState
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalComposeUiApi::class, ExperimentalTransitionApi::class)
@Composable
fun OverviewAppBarBody(
    state: OverviewAppBarState,
    onEvent: (OverviewScreenEvent) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (state.unfocus) unfocusLocationSearcher()
        if (state.hideKeyboard) hideKeyboard(onKeyboardHidden = { onEvent(KeyboardHidden) })
        val transition = updateTransition(
            targetState = state.focused,
            label = "OverviewAppBarBody-Transition"
        )

        LocationSearcher(
            modifier = Modifier.weight(1f),
            query = state.query,
            enabled = state.enabled,
            focusedTransition = transition.createChildTransition { focused -> focused },
            onQueryChange = { query -> onEvent(QueryChanged(query)) },
            onFocusChanged = { focused -> onEvent(FocusChanged(focused)) },
            onUpButtonClick = { onEvent(UpButtonClicked) },
            onResetButtonClick = { onEvent(ResetButtonClicked) },
            onDoneImeActionPressed = { onEvent(DoneImeActionClicked) }
        )
        AnimatedOverflowButton(
            modifier = Modifier.padding(end = 2.dp),
            locationSearcherFocusedTransition = transition.createChildTransition { focused ->
                focused
            },
            onClick = { onEvent(OverflowButtonClicked) }
        )
    }
}

@SuppressLint("ComposableNaming")
@Composable
private fun unfocusLocationSearcher() {
    val focusManager = LocalFocusManager.current
    SideEffect { focusManager.clearFocus(true) }
}

@SuppressLint("ComposableNaming")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun hideKeyboard(onKeyboardHidden: () -> Unit) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    SideEffect {
        keyBoardController?.hide()
        onKeyboardHidden()
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
private fun OverviewAppBarBodyNonEmptyUnfocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewAppBarBody(
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
private fun OverviewAppBarBodyEmptyFocusedEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewAppBarBody(
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
private fun OverviewAppBarBodyEmptyFocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            OverviewAppBarBody(
                state = PreviewData.OverviewAppBarState.EmptyFocusedDisabled,
                onEvent = {}
            )
        }
    }
}