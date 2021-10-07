package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.R

@Composable
fun OverflowButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    LocationSearcherButton(
        modifier = modifier,
        enabled = true,
        icon = Icons.Default.MoreVert,
        iconDescription = stringResource(id = R.string.overflow_button_description),
        iconTint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
        onClick = onClick
    )
}

@Preview(name = "Light")
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun OverflowButtonPreview() {
    Preview {
        OverflowButton(onClick = {})
    }
}