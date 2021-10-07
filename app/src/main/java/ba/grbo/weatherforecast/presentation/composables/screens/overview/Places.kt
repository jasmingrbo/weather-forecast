package ba.grbo.weatherforecast.presentation.composables.screens.overview

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.core.interactors.overview.exceptions.FetchBasicPlacesException
import ba.grbo.core.interactors.overview.exceptions.InsertPlaceException
import ba.grbo.core.interactors.overview.exceptions.ObserveBasicPlacesException
import ba.grbo.weatherforecast.R
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.presentation.composables.PlaceList
import ba.grbo.weatherforecast.presentation.composables.Preview

@Composable
fun Places(
    value: OverviewScreenState.Value<List<BasicPlace>>,
    listState: LazyListState,
    onPlaceClick: (BasicPlace) -> Unit,
    onPlaceLongClick: (BasicPlace) -> Unit,
    onSwipedToRefresh: () -> Unit,
    onSwipedToDismiss: (Long) -> Unit
) {
    OverviewScreenMainContent(
        content = value,
        onContent = { places, clickable ->
            PlaceList(
                places = places,
                clickable = clickable,
                onPlaceClick = onPlaceClick,
                onPlaceLongClick = onPlaceLongClick,
                state = listState,
                onSwipedToRefresh = onSwipedToRefresh,
                onSwipedToDismiss = onSwipedToDismiss
            )
        },
        emptyAnimation = R.raw.places_empty,
        emptyMessage = R.string.places_empty_message,
        onError = { throwable ->
            when (throwable) {
                is ObserveBasicPlacesException,
                is FetchBasicPlacesException -> {
                }
                is InsertPlaceException -> {
                }
            }
        }
    )
}

@Preview(name = "NonEmptySarajevoAndAachen")
@Preview(
    name = "NonEmptySarajevoAndAachen",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun PlacesNonEmptySarajevoAndAachenPreview() {
    Preview {
        Places(
            value = OverviewScreenState.Value.Data(data = PreviewData.Places.SarajevoAndAachen),
            listState = LazyListState(),
            onPlaceClick = {},
            onPlaceLongClick = {},
            onSwipedToRefresh = {},
            onSwipedToDismiss = {}
        )
    }
}

@Preview(name = "NonEmptyOverflow")
@Preview(
    name = "NonEmptyOverflow",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun PlacesNonEmptyOverflowPreview() {
    Preview {
        Places(
            value = OverviewScreenState.Value.Data(data = PreviewData.Places.Overflow),
            listState = LazyListState(),
            onPlaceClick = {},
            onPlaceLongClick = {},
            onSwipedToRefresh = {},
            onSwipedToDismiss = {}
        )
    }
}