package ba.grbo.weatherforecast.framework.mics

import ba.grbo.weatherforecast.AppBarCallables.OverviewAppBarCallables
import ba.grbo.weatherforecast.AppBarState
import ba.grbo.weatherforecast.OverviewAppBarState
import ba.grbo.weatherforecast.framework.mics.Previewable.OverviewAppBarStates

interface Previewable {
    val overviewAppBarStates: OverviewAppBarStates
    val overviewAppBarCallables: OverviewAppBarCallables

    data class OverviewAppBarStates(
        val nonEmptyUnfocusedEnabled: AppBarState.Overview,
        val nonEmptyUnfocusedDisabled: AppBarState.Overview,
        val emptyFocusedEnabled: AppBarState.Overview,
        val emptyFocusedDisabled: AppBarState.Overview,
    )
}

val PreviewData: Previewable by lazy {
    object : Previewable {
        override val overviewAppBarStates = OverviewAppBarStates(
            nonEmptyUnfocusedEnabled = AppBarState.Overview(
                value = OverviewAppBarState(
                    query = "Sarajevo",
                    isFocused = false,
                    isEnabled = true
                )
            ),
            nonEmptyUnfocusedDisabled =  AppBarState.Overview(
                value = OverviewAppBarState(
                    query = "Sarajevo",
                    isFocused = false,
                    isEnabled = false
                )
            ),
            emptyFocusedEnabled =  AppBarState.Overview(
                value = OverviewAppBarState(
                    query = "",
                    isFocused = true,
                    isEnabled = true
                )
            ),
            emptyFocusedDisabled =  AppBarState.Overview(
                value = OverviewAppBarState(
                    query = "",
                    isFocused = true,
                    isEnabled = false
                )
            ),
        )
        override val overviewAppBarCallables = object : OverviewAppBarCallables {
            override val onQueryChange: (String) -> Unit = {}
            override val onFocusChange: (Boolean) -> Unit = {}
            override val onUpClicked: () -> Unit = {}
            override val onResetClicked: () -> Unit = {}
            override val onOverflowClicked: () -> Unit = {}
        }
    }
}