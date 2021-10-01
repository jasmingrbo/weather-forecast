package ba.grbo.weatherforecast.framework.data


sealed class OverviewScreenEvent {
    sealed class AppBarEvent : OverviewScreenEvent() {
        class QueryChanged(val query: String) : AppBarEvent()
        class FocusChanged(val focused: Boolean) : AppBarEvent()
        class EnabledChanged(val enabled: Boolean) : AppBarEvent()
        object UpButtonClicked : AppBarEvent()
        object ResetButtonClicked : AppBarEvent()
        object OverflowButtonClicked : AppBarEvent()
    }

    sealed class KeyboardEvent: OverviewScreenEvent() {
        object DoneImeActionClicked : KeyboardEvent()
        object KeyboardHidden : KeyboardEvent()
    }
}