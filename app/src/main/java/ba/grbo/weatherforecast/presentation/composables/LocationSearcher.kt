package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ba.grbo.core.domain.DEFAULT
import ba.grbo.weatherforecast.framework.mics.Constants.LocationSearcherExitAnimationDuration
import ba.grbo.weatherforecast.framework.mics.PreviewData

@Composable
fun LocationSearcher(
    modifier: Modifier = Modifier,
    query: TextFieldValue,
    enabled: Boolean,
    focusedTransition: Transition<Boolean>,
    onQueryChange: (TextFieldValue) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    onUpButtonClick: () -> Unit,
    onResetButtonClick: () -> Unit,
    onDoneImeActionClick: () -> Unit
) {
    val endPadding by createHorizontalPadding(
        focusedTransition = focusedTransition,
        focusedPadding = 12.dp,
        unfocusedPadding = 2.dp
    )

    BasicTextField(
        modifier = modifier
            .padding(start = 12.dp, end = endPadding, top = 8.dp, bottom = 8.dp)
            .onFocusChanged { onFocusChanged(it.isFocused) },
        value = query,
        onValueChange = onQueryChange,
        textStyle = LocalTextStyle.current.copy(
            color = LocalContentColor.current.copy(
                if (enabled) LocalContentAlpha.current else ContentAlpha.disabled
            )
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { onDoneImeActionClick() }),
        singleLine = true,
        enabled = enabled,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        decorationBox = { innerTextField ->
            LocationSearcherDecorationBox(
                query = query,
                enabled = enabled,
                focusedTransition = focusedTransition,
                innerTextField = innerTextField,
                onUpButtonClick = onUpButtonClick,
                onResetButtonClick = onResetButtonClick
            )
        }
    )
}

@Composable
private fun createHorizontalPadding(
    focusedTransition: Transition<Boolean>,
    focusedPadding: Dp,
    unfocusedPadding: Dp
) = focusedTransition.animateDp(
    transitionSpec = {
        tween(
            durationMillis = if (targetState) LocationSearcherExitAnimationDuration else DefaultDurationMillis,
            easing = if (targetState) FastOutLinearInEasing else LinearOutSlowInEasing
        )
    },
    label = "startPadding"
) { isFocused -> if (isFocused) focusedPadding else unfocusedPadding }

@Preview(name = "NonEmptyUnfocusedEnabled")
@Preview(
    name = "NonEmptyUnfocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherNonEmptyUnfocusedEnabledPreview() {
    Preview {
        LocationSearcher(
            query = PreviewData.Query.Sarajevo,
            enabled = true,
            focusedTransition = updateTransition(targetState = false, label = String.DEFAULT),
            onQueryChange = {},
            onFocusChanged = {},
            onUpButtonClick = {},
            onResetButtonClick = {},
            onDoneImeActionClick = {}
        )
    }
}

@Preview(name = "NonEmptyUnfocusedDisabled")
@Preview(
    name = "NonEmptyUnfocusedDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherNonEmptyUnfocusedDisabledPreview() {
    Preview {
        LocationSearcher(
            query = PreviewData.Query.Sarajevo,
            enabled = false,
            focusedTransition = updateTransition(targetState = false, label = String.DEFAULT),
            onQueryChange = {},
            onFocusChanged = {},
            onUpButtonClick = {},
            onResetButtonClick = {},
            onDoneImeActionClick = {}
        )
    }
}

@Preview(name = "EmptyFocusedEnabled")
@Preview(
    name = "EmptyFocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherEmptyFocusedEnabledPreview() {
    Preview {
        LocationSearcher(
            query = PreviewData.Query.Empty,
            enabled = true,
            focusedTransition = updateTransition(targetState = true, label = String.DEFAULT),
            onQueryChange = {},
            onFocusChanged = {},
            onUpButtonClick = {},
            onResetButtonClick = {},
            onDoneImeActionClick = {}
        )
    }
}

@Preview(name = "EmptyFocusedDisabled")
@Preview(
    name = "EmptyFocusedDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherEmptyFocusedDisabledPreview() {
    Preview {
        LocationSearcher(
            query = PreviewData.Query.Empty,
            enabled = false,
            focusedTransition = updateTransition(targetState = true, label = String.DEFAULT),
            onQueryChange = {},
            onFocusChanged = {},
            onUpButtonClick = {},
            onResetButtonClick = {},
            onDoneImeActionClick = {}
        )
    }
}