package ba.grbo.weatherforecast.framework.data.states

import androidx.compose.ui.text.input.TextFieldValue
import ba.grbo.core.domain.dm.Location


sealed class OverviewScreenEvent {
    sealed class AppBarEvent : OverviewScreenEvent() {
        class QueryChanged(val query: TextFieldValue) : AppBarEvent()
        class FocusChanged(val focused: Boolean) : AppBarEvent()
        class EnabledChanged(val enabled: Boolean) : AppBarEvent()
        object UpButtonClicked : AppBarEvent()
        object ClearButtonClicked : AppBarEvent()
        object ClearQueryHistoryButtonClicked : AppBarEvent()
        object DoneImeActionClicked : AppBarEvent()
        object KeyboardHidden : AppBarEvent()
        object OverflowButtonClicked : AppBarEvent()
    }

    sealed class BodyEvent : OverviewScreenEvent() {
        class InternetStatusChanged(val internet: Boolean) : BodyEvent()
        class BasicPlaceClicked(val placeId: Long) : BodyEvent()
        class BasicPlaceLongClicked(val placeId: Long) : BodyEvent()
        object SwipedToRefresh : BodyEvent()
        class SwipedToDismiss(val placeId: Long): BodyEvent()
        class QueryClicked(val query: String) : BodyEvent()
        class LocationClicked(val location: Location) : BodyEvent()
    }
}