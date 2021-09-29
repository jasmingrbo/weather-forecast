package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InnerTextField(
    modifier: Modifier = Modifier,
    query: TextFieldValue,
    enabled: Boolean,
    innerTextField: @Composable () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        innerTextField()
        AnimatedHint(query = query, enabled = enabled)
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
private fun InnerTextFieldNonEmptyEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            InnerTextField(
                modifier = Modifier.fillMaxWidth(),
                query = PreviewData.Query.NonEmpty,
                enabled = true,
                innerTextField = { Text(text = PreviewData.Query.NonEmpty.text) },
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
private fun InnerTextFieldNonEmptyDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            InnerTextField(
                modifier = Modifier.fillMaxWidth(),
                query = PreviewData.Query.NonEmpty,
                enabled = false,
                innerTextField = { Text(text = PreviewData.Query.NonEmpty.text) },
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
private fun InnerTextFieldEmptyEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            InnerTextField(
                modifier = Modifier.fillMaxWidth(),
                query = PreviewData.Query.Empty,
                enabled = true,
                innerTextField = { Text(text = PreviewData.Query.Empty.text) },
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
private fun InnerTextFieldEmptyDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            InnerTextField(
                modifier = Modifier.fillMaxWidth(),
                query = PreviewData.Query.Empty,
                enabled = false,
                innerTextField = { Text(text = PreviewData.Query.Empty.text) },
            )
        }
    }
}