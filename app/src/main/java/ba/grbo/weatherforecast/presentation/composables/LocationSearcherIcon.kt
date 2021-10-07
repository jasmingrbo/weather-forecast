package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LocationSearcherIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    enabled: Boolean,
    tint: Color = MaterialTheme.colors.onSurface.copy(
        alpha = if (enabled) TextFieldDefaults.IconOpacity else ContentAlpha.disabled
    )
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Preview(name = "Enabled")
@Preview(
    name = "Enabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherIconAddEnabledPreview() {
    Preview {
        LocationSearcherIcon(
            imageVector = Icons.Default.Add,
            contentDescription = "",
            enabled = true
        )
    }
}

@Preview(name = "Disabled")
@Preview(
    name = "Disabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherIconRefreshDisabledPreview() {
    Preview {
        LocationSearcherIcon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "",
            enabled = false
        )
    }
}
