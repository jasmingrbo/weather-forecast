package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.core.AnimationConstants
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
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.framework.mics.EMPTY
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LocationSearcher(
    modifier: Modifier = Modifier,
    query: TextFieldValue,
    enabled: Boolean,
    hideKeyboard: Boolean,
    focusedTransition: Transition<Boolean>,
    onQueryChange: (TextFieldValue) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    onUpButtonClick: () -> Unit,
    onResetButtonClick: () -> Unit,
    onDoneImeActionPressed: () -> Unit,
    onSoftwareKeyboardHidden: () -> Unit
) {
    val endPadding by createHorizontalPadding(
        focusedTransition = focusedTransition,
        focusedPadding = 12.dp,
        unfocusedPadding = 2.dp
    )

    if (hideKeyboard) {
        LocalSoftwareKeyboardController.current?.hide()
        onSoftwareKeyboardHidden()
    }

    BasicTextField(
        modifier = modifier
            .padding(start = 12.dp, end = endPadding, top = 8.dp, bottom = 8.dp)
            .onFocusChanged { onFocusChanged(it.isFocused) },
        value = query,
        onValueChange = onQueryChange,
        textStyle = LocalTextStyle.current.copy(
            color = LocalContentColor.current.copy(
                if (enabled) LocalContentAlpha.current
                else ContentAlpha.disabled
            )
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { onDoneImeActionPressed() }),
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
            durationMillis = if (targetState) 200 else AnimationConstants.DefaultDurationMillis,
            easing = if (targetState) FastOutLinearInEasing else LinearOutSlowInEasing
        )
    },
    label = "startPadding"
) { isFocused -> if (isFocused) focusedPadding else unfocusedPadding }

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
private fun LocationSearcherNonEmptyUnfocusedEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            LocationSearcher(
                query = PreviewData.Query.NonEmpty,
                enabled = true,
                hideKeyboard = false,
                focusedTransition = updateTransition(targetState = false, label = String.EMPTY),
                onQueryChange = {},
                onFocusChanged = {},
                onUpButtonClick = {},
                onResetButtonClick = {},
                onDoneImeActionPressed = {},
                onSoftwareKeyboardHidden = {}
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
private fun LocationSearcherNonEmptyUnfocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            LocationSearcher(
                query = PreviewData.Query.NonEmpty,
                enabled = false,
                hideKeyboard = false,
                focusedTransition = updateTransition(targetState = false, label = String.EMPTY),
                onQueryChange = {},
                onFocusChanged = {},
                onUpButtonClick = {},
                onResetButtonClick = {},
                onDoneImeActionPressed = {},
                onSoftwareKeyboardHidden = {}
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
private fun LocationSearcherEmptyFocusedEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            LocationSearcher(
                query = PreviewData.Query.Empty,
                enabled = true,
                hideKeyboard = false,
                focusedTransition = updateTransition(targetState = true, label = String.EMPTY),
                onQueryChange = {},
                onFocusChanged = {},
                onUpButtonClick = {},
                onResetButtonClick = {},
                onDoneImeActionPressed = {},
                onSoftwareKeyboardHidden = {}
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
private fun LocationSearcherEmptyFocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            LocationSearcher(
                query = PreviewData.Query.Empty,
                enabled = false,
                hideKeyboard = false,
                focusedTransition = updateTransition(targetState = true, label = String.EMPTY),
                onQueryChange = {},
                onFocusChanged = {},
                onUpButtonClick = {},
                onResetButtonClick = {},
                onDoneImeActionPressed = {},
                onSoftwareKeyboardHidden = {}
            )
        }
    }
}