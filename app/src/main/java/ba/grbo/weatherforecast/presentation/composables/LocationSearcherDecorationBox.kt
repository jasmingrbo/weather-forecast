package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@Composable
fun LocationSearcherDecorationBox(
    query: String,
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
            hintVisible = query.isEmpty(),
            enabled = enabled,
            innerTextField = innerTextField
        )
        AnimatedResetButton(
            modifier = Modifier.padding(start = 4.dp, end = 0.dp),
            visible = query.isNotEmpty(),
            enabled = enabled,
            onClick = onResetButtonClick
        )
    }
}

@Preview(
    name = "Light-NonEmptyEnabled",
    showBackground = true
)
@Preview(
    name = "Dark-NonEmptyEnabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherDecorationBoxNonEmptyEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            LocationSearcherDecorationBox(
                query = PreviewData.Query.NonEmpty,
                enabled = true,
                focusedTransition = updateTransition(targetState = false, label = ""),
                innerTextField = { Text(text = PreviewData.Query.NonEmpty) },
                onUpButtonClick = {},
                onResetButtonClick = {}
            )
        }
    }
}

@Preview(
    name = "Light-NonEmptyDisabled",
    showBackground = true
)
@Preview(
    name = "Dark-NonEmptyDisabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherDecorationBoxNonEmptyDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            LocationSearcherDecorationBox(
                query = PreviewData.Query.NonEmpty,
                enabled = false,
                focusedTransition = updateTransition(targetState = false, label = ""),
                innerTextField = { Text(text = PreviewData.Query.NonEmpty) },
                onUpButtonClick = {},
                onResetButtonClick = {}
            )
        }
    }
}

@Preview(
    name = "Light-EmptyEnabled",
    showBackground = true
)
@Preview(
    name = "Dark-EmptyEnabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherDecorationBoxEmptyEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            LocationSearcherDecorationBox(
                query = PreviewData.Query.Empty,
                enabled = true,
                focusedTransition = updateTransition(targetState = true, label = ""),
                innerTextField = { Text(text = PreviewData.Query.Empty) },
                onUpButtonClick = {},
                onResetButtonClick = {}
            )
        }
    }
}

@Preview(
    name = "Light-EmptyDisabled",
    showBackground = true
)
@Preview(
    name = "Dark-EmptyDisabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherDecorationBoxEmptyDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            LocationSearcherDecorationBox(
                query = PreviewData.Query.Empty,
                enabled = false,
                focusedTransition = updateTransition(targetState = true, label = ""),
                innerTextField = { Text(text = PreviewData.Query.Empty) },
                onUpButtonClick = {},
                onResetButtonClick = {}
            )
        }
    }
}