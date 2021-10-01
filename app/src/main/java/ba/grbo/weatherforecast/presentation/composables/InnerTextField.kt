package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@Composable
fun InnerTextField(
    modifier: Modifier = Modifier,
    hintVisible: Boolean,
    enabled: Boolean,
    innerTextField: @Composable () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        innerTextField()
        AnimatedHint(visible = hintVisible, enabled = enabled)
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
                hintVisible = false,
                enabled = true,
                innerTextField = { Text(text = PreviewData.Query.NonEmpty) },
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
                hintVisible = false,
                enabled = false,
                innerTextField = { Text(text = PreviewData.Query.NonEmpty) },
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
                hintVisible = true,
                enabled = true,
                innerTextField = { Text(text = PreviewData.Query.Empty) },
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
                hintVisible = true,
                enabled = false,
                innerTextField = { Text(text = PreviewData.Query.Empty) },
            )
        }
    }
}