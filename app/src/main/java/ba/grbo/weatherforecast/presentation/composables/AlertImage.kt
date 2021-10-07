package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.R

@Composable
fun AlertImage() {
    CustomImage(
        modifier = Modifier.size(24.dp),
        image = R.drawable.ic_alert,
        description = R.string.alert_description,
        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.error)
    )
    HorizontalSpace(space = 4.dp)
}

@Preview(name = "Alert")
@Preview(
    name = "Alert",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AlertImagePreview() {
    Preview {
        AlertImage()
    }
}
