package ba.grbo.weatherforecast.presentation.composables.screens.overview

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.core.domain.dm.Location
import ba.grbo.core.interactors.overview.exceptions.FetchLocationsException
import ba.grbo.weatherforecast.R
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Data
import ba.grbo.weatherforecast.framework.mics.PreviewData.Locations.Sarajevo
import ba.grbo.weatherforecast.presentation.composables.Error
import ba.grbo.weatherforecast.presentation.composables.LocationList
import ba.grbo.weatherforecast.presentation.composables.Preview

@Composable
fun Locations(
    value: OverviewScreenState.Value<List<Location>>,
    onLocationClick: (Location) -> Unit
) {
    OverviewScreenMainContent(
        content = value,
        onContent = { locations, clickable ->
            LocationList(
                locations = locations,
                clickable = clickable,
                onLocationClick = onLocationClick
            )
        },
        emptyAnimation = R.raw.locations_empty,
        emptyMessage = R.string.locations_empty_message,
        onError = { throwable ->
            val query = (throwable as FetchLocationsException).query

            Error(
                animation = R.raw.locations_error,
                message = R.string.locations_error_message,
                actionMessage = R.string.locations_action_message
            )
        }
    )
}

@Preview(name = "NonEmptySarajevo")
@Preview(
    name = "NonEmptySarajevo",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun LocationSarajevoPreview() {
    Preview {
        Locations(
            value = Data(data = Sarajevo),
            onLocationClick = {}
        )
    }
}