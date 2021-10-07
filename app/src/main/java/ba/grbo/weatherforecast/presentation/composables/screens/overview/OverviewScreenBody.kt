package ba.grbo.weatherforecast.presentation.composables.screens.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.core.domain.dm.Location
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Content.Locations
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Content.Places
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Content.Queries
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value
import ba.grbo.weatherforecast.framework.theme.DarkBackground
import ba.grbo.weatherforecast.framework.theme.LightBackground
import ba.grbo.weatherforecast.presentation.composables.AnimatedInternetStatusBanner
import ba.grbo.weatherforecast.presentation.composables.AnimatedLinearProgressIndicator
import ba.grbo.weatherforecast.presentation.composables.keyboardAsState

@Composable
fun ColumnScope.OverviewScreenBody(
    appBarQuery: String,
    appBarFocused: Boolean,
    loading: Boolean,
    internet: Boolean,
    places: Value<List<BasicPlace>>,
    queries: Value<List<String>>,
    locations: Value<List<Location>>,
    onEvent: (BodyEvent) -> Unit,
    onPlaceClicked: (Long) -> Unit
) {
    AnimatedInternetStatusBanner(internet = internet)
    Box(modifier = Modifier.weight(1f)) {
        AnimatedLinearProgressIndicator(loading = loading)
        AnimatedOverviewScreenMainContent(
            modifier = Modifier.background(MaterialTheme.colors.background),
            content = when {
                !appBarFocused -> Places(places)
                appBarQuery.length < 2 -> Queries(queries)
                appBarQuery.length >= 2 -> Locations(locations)
                else -> throw IllegalArgumentException("Unknown app state: appBarFocused: $appBarFocused, appBarQuery: $appBarQuery")
            },
            onEvent = onEvent,
            onPlaceClicked = onPlaceClicked
        )
    }

    val keyboardOpened by keyboardAsState()
    Divider(
        color = if (!appBarFocused || !keyboardOpened) MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
        else if (MaterialTheme.colors.isLight) LightBackground else DarkBackground,
        thickness = 0.5.dp
    )
}

// add previews