package ba.grbo.weatherforecast.presentation.composables.screens.overview

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.createChildTransition
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.ClearButtonClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.DoneImeActionClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.FocusChanged
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.KeyboardHidden
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.OverflowButtonClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.QueryChanged
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.UpButtonClicked
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.presentation.composables.AnimatedOverflowButton
import ba.grbo.weatherforecast.presentation.composables.LocationSearcher
import ba.grbo.weatherforecast.presentation.composables.Preview
import ba.grbo.weatherforecast.presentation.composables.keyboardAsState

@OptIn(ExperimentalTransitionApi::class)
@Composable
fun OverviewScreenAppBarContent(
    query: TextFieldValue,
    focused: Boolean,
    enabled: Boolean,
    unfocus: Boolean,
    hideKeyboard: Boolean,
    onEvent: (AppBarEvent) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (unfocus) unfocusLocationSearcher()
        if (hideKeyboard) hideKeyboard(onKeyboardHidden = { onEvent(KeyboardHidden) })
        val transition = updateTransition(
            targetState = focused,
            label = "OverviewScreenAppBarBody-Transition"
        )

        LocationSearcher(
            modifier = Modifier.weight(1f),
            query = query,
            enabled = enabled,
            focusedTransition = transition.createChildTransition { focused -> focused },
            onQueryChange = { query -> onEvent(QueryChanged(query)) },
            onFocusChanged = { focused -> onEvent(FocusChanged(focused)) },
            onUpButtonClick = { onEvent(UpButtonClicked) },
            onResetButtonClick = { onEvent(ClearButtonClicked) },
            onDoneImeActionClick = { onEvent(DoneImeActionClicked) }
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
    val keyboardOpened by keyboardAsState()
    SideEffect {
        keyBoardController?.hide()
        if (!keyboardOpened) onKeyboardHidden()
    }
}

@Preview(name = "NonEmptyUnfocusedEnabled")
@Preview(
    name = "NonEmptyUnfocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun OverviewAppBarContentNonEmptyUnfocusedEnabledPreview() {
    Preview {
        OverviewScreenAppBarContent(
            query = PreviewData.Query.Sarajevo,
            focused = false,
            enabled = true,
            unfocus = false,
            hideKeyboard = false,
            onEvent = {}
        )
    }
}

@Preview(name = "NonEmptyUnfocusedDisabled")
@Preview(
    name = "NonEmptyUnfocusedDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun OverviewAppBarContentNonEmptyUnfocusedDisabledPreview() {
    Preview {
        OverviewScreenAppBarContent(
            query = PreviewData.Query.Sarajevo,
            focused = false,
            enabled = false,
            unfocus = false,
            hideKeyboard = false,
            onEvent = {}
        )
    }
}

@Preview(name = "EmptyFocusedEnabled")
@Preview(
    name = "EmptyFocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun OverviewAppBarContentEmptyFocusedEnabledPreview() {
    Preview {
        OverviewScreenAppBarContent(
            query = PreviewData.Query.Empty,
            focused = true,
            enabled = true,
            unfocus = false,
            hideKeyboard = false,
            onEvent = {}
        )
    }
}

@Preview(name = "EmptyFocusedDisabled")
@Preview(
    name = "EmptyFocusedDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun OverviewAppBarContentEmptyFocusedDisabledPreview() {
    Preview {
        OverviewScreenAppBarContent(
            query = PreviewData.Query.Empty,
            focused = true,
            enabled = false,
            unfocus = false,
            hideKeyboard = false,
            onEvent = {}
        )
    }
}