package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.core.domain.dm.Location
import ba.grbo.weatherforecast.framework.mics.PreviewData.Locations.Sarajevo

@Composable
fun LocationList(
    locations: List<Location>,
    clickable: Boolean,
    onLocationClick: (Location) -> Unit,
) {
    SimpleList(
        items = locations,
        text = { location -> "${location.name}, ${location.address}" },
        key = { _, location -> location.coordinates.toString() },
        clickable = clickable,
        onItemCardClick = onLocationClick,
        icon = { location ->
            val modifier = if (MaterialTheme.colors.isLight) Modifier
                .padding(start = 3.5.dp, end = 19.5.dp)
                .size(24.dp)
            else Modifier
                .padding(start = 4.dp, end = 20.dp)
                .size(24.dp)

            CountryFlag(
                modifier = modifier,
                flag = location.countryFlagResource
            )
        }
    )
}


@Preview(name = "NonEmptySarajevo")
@Preview(
    name = "NonEmptySarajevo",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LocationListNonEmptySarajevoPreview() {
    Preview {
        LocationList(
            locations = Sarajevo,
            clickable = true,
            onLocationClick = {}
        )
    }
}
