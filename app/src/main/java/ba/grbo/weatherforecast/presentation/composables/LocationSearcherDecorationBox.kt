package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.core.domain.DEFAULT
import ba.grbo.weatherforecast.framework.mics.PreviewData

@Composable
fun LocationSearcherDecorationBox(
    query: TextFieldValue,
    enabled: Boolean,
    focusedTransition: Transition<Boolean>,
    innerTextField: @Composable () -> Unit,
    onUpButtonClick: () -> Unit,
    onResetButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colors.onSurface.copy(
                    alpha = TextFieldDefaults.BackgroundOpacity
                ),
                shape = CircleShape
            )
            .heightIn(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedLeadingIcon(
            locationSearcherFocusedTransition = focusedTransition,
            enabled = enabled,
            onClick = onUpButtonClick
        )
        InnerTextField(
            modifier = Modifier.weight(1f),
            hintVisible = query.text.isEmpty(),
            enabled = enabled,
            innerTextField = innerTextField
        )
        AnimatedClearButton(
            modifier = Modifier.padding(start = 4.dp, end = 0.dp),
            visible = query.text.isNotEmpty(),
            enabled = enabled,
            onClick = onResetButtonClick
        )
    }
}

@Preview(name = "NonEmptyUnfocusedEnabled")
@Preview(
    name = "NonEmptyUnfocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherDecorationBoxNonEmptyUnfocusedEnabledPreview() {
    Preview {
        LocationSearcherDecorationBox(
            query = PreviewData.Query.Sarajevo,
            enabled = true,
            focusedTransition = updateTransition(targetState = false, label = String.DEFAULT),
            innerTextField = { Text(text = PreviewData.Query.Sarajevo.text) },
            onUpButtonClick = {},
            onResetButtonClick = {}
        )
    }
}

@Preview(name = "NonEmptyUnfocusedDisabled")
@Preview(
    name = "NonEmptyUnfocusedDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherDecorationBoxNonEmptyUnfocusedDisabledPreview() {
    Preview {
        LocationSearcherDecorationBox(
            query = PreviewData.Query.Sarajevo,
            enabled = false,
            focusedTransition = updateTransition(targetState = false, label = String.DEFAULT),
            innerTextField = { Text(text = PreviewData.Query.Sarajevo.text) },
            onUpButtonClick = {},
            onResetButtonClick = {}
        )
    }
}

@Preview(name = "EmptyFocusedEnabled")
@Preview(
    name = "EmptyFocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherDecorationBoxEmptyFocusedEnabledPreview() {
    Preview {
        LocationSearcherDecorationBox(
            query = PreviewData.Query.Empty,
            enabled = true,
            focusedTransition = updateTransition(targetState = true, label = String.DEFAULT),
            innerTextField = { Text(text = PreviewData.Query.Empty.text) },
            onUpButtonClick = {},
            onResetButtonClick = {}
        )
    }
}

@Preview(name = "EmptyFocusedDisabled")
@Preview(
    name = "EmptyFocusedDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherDecorationBoxEmptyFocusedDisabledPreview() {
    Preview {
        LocationSearcherDecorationBox(
            query = PreviewData.Query.Empty,
            enabled = false,
            focusedTransition = updateTransition(targetState = true, label = String.DEFAULT),
            innerTextField = { Text(text = PreviewData.Query.Empty.text) },
            onUpButtonClick = {},
            onResetButtonClick = {}
        )
    }
}