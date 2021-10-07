package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.R

@Composable
fun ClearButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    LocationSearcherButton(
        modifier = modifier,
        enabled = enabled,
        icon = Icons.Default.Clear,
        iconDescription = stringResource(id = R.string.clear_button_description),
        onClick = onClick
    )
}

@Preview(name = "Enabled")
@Preview(
    name = "Enabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun ClearButtonEnabledPreview() {
    Preview {
        ClearButton(
            enabled = true,
            onClick = {}
        )
    }
}

@Preview(name = "Disabled")
@Preview(
    name = "Disabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun ClearButtonDisabledPreview() {
    Preview {
        ClearButton(
            enabled = false,
            onClick = {}
        )
    }
}