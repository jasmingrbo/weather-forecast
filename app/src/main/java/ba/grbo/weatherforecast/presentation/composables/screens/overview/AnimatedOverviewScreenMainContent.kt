package ba.grbo.weatherforecast.presentation.composables.screens.overview

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.BasicPlaceClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.BasicPlaceLongClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.LocationClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.QueryClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.SwipedToDismiss
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.SwipedToRefresh
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Content
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Content.Locations
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Content.Places
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Content.Queries

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedOverviewScreenMainContent(
    modifier: Modifier = Modifier,
    content: Content,
    onEvent: (BodyEvent) -> Unit,
    onPlaceClicked: (Long) -> Unit
) {
    val placesListState = rememberLazyListState()
    Crossfade(
        modifier = modifier,
        targetState = content::class
    ) { targetContent ->
        when (targetContent) {
            Places::class -> if (content is Places) Places(
                value = content.value,
                listState = placesListState,
                onPlaceClick = { place ->
                    onEvent(BasicPlaceClicked(place.id)) // to disable clicking
                    onPlaceClicked(place.id)
                },
                onPlaceLongClick = { place -> onEvent(BasicPlaceLongClicked(place.id)) },
                onSwipedToRefresh = { onEvent(SwipedToRefresh) },
                onSwipedToDismiss = { id -> onEvent(SwipedToDismiss(id)) }
            )
            Queries::class -> if (content is Queries) Queries(
                value = content.value,
                onQueryClick = { query -> onEvent(QueryClicked(query)) }
            )
            Locations::class -> if (content is Locations) Locations(
                value = content.value,
                onLocationClick = { location -> onEvent(LocationClicked(location)) }
            )
        }
    }
}


// add previews