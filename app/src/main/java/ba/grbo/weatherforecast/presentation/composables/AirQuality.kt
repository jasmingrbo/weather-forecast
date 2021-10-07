package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.R

@Composable
fun AirQualityIndex(@DrawableRes airQuality: Int) {
    CustomImage(
        modifier = Modifier.size(18.dp),
        image = airQuality,
        description = R.string.air_quality_description
    )
}

@Preview(name = "Good")
@Preview(
    name = "Good",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AirQualityIndexGoodPreview() {
    Preview {
        AirQualityIndex(airQuality = R.drawable.ic_aq_good)
    }
}

@Preview(name = "VeryPoor")
@Preview(
    name = "VeryPoor",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AirQualityIndexVeryPoorPreview() {
    Preview {
        AirQualityIndex(airQuality = R.drawable.ic_aq_very_poor)
    }
}