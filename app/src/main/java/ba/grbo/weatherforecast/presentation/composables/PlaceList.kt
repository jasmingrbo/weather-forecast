package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.weatherforecast.framework.mics.PreviewData.Places.Overflow
import ba.grbo.weatherforecast.framework.mics.PreviewData.Places.SarajevoAndAachen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun PlaceList(
    places: List<BasicPlace>,
    clickable: Boolean,
    onPlaceClick: (BasicPlace) -> Unit,
    onPlaceLongClick: (BasicPlace) -> Unit,
    state: LazyListState,
    onSwipedToRefresh: () -> Unit,
    onSwipedToDismiss: (Long) -> Unit
) {
    SwipeRefresh(
        modifier = Modifier.zIndex(1f),
        state = rememberSwipeRefreshState(isRefreshing = false),
        onRefresh = { onSwipedToRefresh() }
    ) {
        LaunchedEffect(key1 = places) {
            if (places[0].weather.stale) state.scrollToItem(0)
        }

        CustomLazyColumn(
            modifier = Modifier.fillMaxHeight(), // so that swipe refresh works on the whole screen
            items = places,
            key = { _, place -> place.coordinates.toString() },
            state = state
        ) { index, place ->
            SwipeToDismissPlace(
                last = index == places.lastIndex,
                onSwipedToDismiss = { onSwipedToDismiss(place.id) }
            ) {
                PlaceListItem(
                    place = place,
                    clickable = clickable,
                    last = index == places.lastIndex,
                    onClick = onPlaceClick,
                    onLongClick = onPlaceLongClick
                )
            }
        }
    }
}

@Preview(name = "SarajevoAndAachen")
@Preview(
    name = "SarajevoAndAachen",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PlaceListSarajevoAndAachenPreview() {
    Preview {
        PlaceList(
            places = SarajevoAndAachen,
            clickable = true,
            onPlaceClick = {},
            onPlaceLongClick = {},
            state = LazyListState(),
            onSwipedToRefresh = { },
            onSwipedToDismiss = {}
        )
    }
}

@Preview(name = "Overflow")
@Preview(
    name = "Overflow",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun PlaceListOverflowPreview() {
    Preview {
        PlaceList(
            places = Overflow,
            clickable = true,
            onPlaceClick = {},
            onPlaceLongClick = {},
            state = LazyListState(),
            onSwipedToRefresh = { },
            onSwipedToDismiss = {}
        )
    }
}