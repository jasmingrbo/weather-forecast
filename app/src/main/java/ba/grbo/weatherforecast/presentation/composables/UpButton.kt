package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.R

@Composable
fun UpButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    LocationSearcherButton(
        modifier = modifier,
        enabled = enabled,
        icon = Icons.Default.ArrowBack,
        iconDescription = stringResource(id = R.string.up_button_description),
        onClick = onClick
    )
}

@Preview(name = "Enabled")
@Preview(
    name = "Enabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun UpButtonEnabledPreview() {
    Preview {
        UpButton(
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
private fun UpButtonDisabledPreview() {
    Preview {
        UpButton(
            enabled = false,
            onClick = {}
        )
    }
}