package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.R

@Composable
fun MagnifierIcon(
    modifier: Modifier = Modifier,
    enabled: Boolean,
) {
    LocationSearcherIcon(
        modifier = modifier,
        imageVector = Icons.Default.Search,
        contentDescription = stringResource(id = R.string.magnifier_icon_description),
        enabled = enabled
    )
}

@Preview(name = "Enabled")
@Preview(
    name = "Enabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun MagnifierIconEnabledPreview() {
    Preview {
        MagnifierIcon(enabled = true)
    }
}

@Preview(name = "Disabled")
@Preview(
    name = "Disabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun MagnifierIconDisabledPreview() {
    Preview {
        MagnifierIcon(enabled = false)
    }
}