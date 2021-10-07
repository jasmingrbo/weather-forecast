package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.R

@Composable
fun Hint(enabled: Boolean) {
    Text(
        text = stringResource(id = R.string.location_searcher_hint),
        style = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface.copy(
                if (enabled) ContentAlpha.medium else ContentAlpha.disabled
            )
        )
    )
}

@Preview(name = "Enabled")
@Preview(
    name = "Enabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun HintEnabledPreview() {
    Preview {
        Hint(enabled = true)
    }
}

@Preview(name = "Disabled")
@Preview(
    name = "Disabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun HintDisabledPreview() {
    Preview {
        Hint(enabled = false)
    }
}