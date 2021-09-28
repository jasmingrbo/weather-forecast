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
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InnerTextField(
    modifier: Modifier = Modifier,
    query: String,
    isEnabled: Boolean,
    innerTextField: @Composable () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        innerTextField()
        AnimatedHint(query = query, isEnabled = isEnabled)
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
                query = "Sarajevo",
                isEnabled = true,
                innerTextField = { Text(text = "Sarajevo") },
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
                query = "Sarajevo",
                isEnabled = false,
                innerTextField = { Text(text = "Sarajevo") },
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
                query = "",
                isEnabled = true,
                innerTextField = { Text(text = "") },
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
                query = "",
                isEnabled = false,
                innerTextField = { Text(text = "") },
            )
        }
    }
}