package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LocationSearcherButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    icon: ImageVector,
    iconDescription: String,
    iconTint: Color = MaterialTheme.colors.onSurface.copy(
        alpha = if (enabled) TextFieldDefaults.IconOpacity else ContentAlpha.disabled
    ),
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        LocationSearcherIcon(
            imageVector = icon,
            contentDescription = iconDescription,
            enabled = enabled,
            tint = iconTint
        )
    }
}

@Preview(name = "Enabled")
@Preview(
    name = "Enabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSearcherButtonDeleteEnabledPreview() {
    Preview {
        LocationSearcherButton(
            enabled = true,
            icon = Icons.Default.Delete,
            iconDescription = "",
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
private fun LocationSearcherButtonHomeDisabledPreview() {
    Preview {
        LocationSearcherButton(
            enabled = false,
            icon = Icons.Default.Home,
            iconDescription = "",
            onClick = {}
        )
    }
}