package ba.grbo.weatherforecast.ui.composables

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
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
import ba.grbo.weatherforecast.ui.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LocationSearcherDecorationBox(
    query: String,
    isEnabled: Boolean,
    isFocusedTransition: Transition<Boolean>,
    innerTextField: @Composable () -> Unit,
    onUpClick: () -> Unit,
    onResetClick: () -> Unit
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
            isFocusedTransition = isFocusedTransition,
            isEnabled = isEnabled,
            onUpClick = onUpClick
        )
        InnerTextField(
            modifier = Modifier.weight(1f),
            query = query,
            isEnabled = isEnabled,
            innerTextField = innerTextField
        )
        AnimatedResetButton(
            modifier = Modifier.padding(start = 4.dp, end = 0.dp),
            query = query,
            isEnabled = isEnabled,
            onClick = onResetClick
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
                query = "Sarajevo",
                isEnabled = true,
                isFocusedTransition = updateTransition(targetState = false, label = ""),
                innerTextField = { Text(text = "Sarajevo") },
                onUpClick = {},
                onResetClick = {}
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
                query = "Sarajevo",
                isEnabled = false,
                isFocusedTransition = updateTransition(targetState = false, label = ""),
                innerTextField = { Text(text = "Sarajevo") },
                onUpClick = {},
                onResetClick = {}
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
                query = "",
                isEnabled = true,
                isFocusedTransition = updateTransition(targetState = true, label = ""),
                innerTextField = { Text(text = "") },
                onUpClick = {},
                onResetClick = {}
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
                query = "",
                isEnabled = false,
                isFocusedTransition = updateTransition(targetState = true, label = ""),
                innerTextField = { Text(text = "") },
                onUpClick = {},
                onResetClick = {}
            )
        }
    }
}