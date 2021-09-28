package ba.grbo.weatherforecast.framework.mics

import ba.grbo.weatherforecast.framework.data.CommonBodyState
import ba.grbo.weatherforecast.framework.mics.Previewable.OverviewAppBarStates

interface Previewable {
    val overviewAppBarStates: OverviewAppBarStates

    data class OverviewAppBarStates(
        val nonEmptyUnfocusedEnabled: CommonBodyState.AppBarState.Overview,
        val nonEmptyUnfocusedDisabled: CommonBodyState.AppBarState.Overview,
        val emptyFocusedEnabled: CommonBodyState.AppBarState.Overview,
        val emptyFocusedDisabled: CommonBodyState.AppBarState.Overview,
    )
}

val PreviewData: Previewable by lazy {
    object : Previewable {
        override val overviewAppBarStates = OverviewAppBarStates(
            nonEmptyUnfocusedEnabled = CommonBodyState.AppBarState.Overview(
                value = CommonBodyState.AppBarState.Overview.OverviewAppBarState(
                    query = "Sarajevo",
                    focused = false,
                    enabled = true,
                    unfocus = false,
                    hideKeyboard = false
                )
            ),
            nonEmptyUnfocusedDisabled =  CommonBodyState.AppBarState.Overview(
                value = CommonBodyState.AppBarState.Overview.OverviewAppBarState(
                    query = "Sarajevo",
                    focused = false,
                    enabled = false,
                    unfocus = false,
                    hideKeyboard = false
                )
            ),
            emptyFocusedEnabled =  CommonBodyState.AppBarState.Overview(
                value = CommonBodyState.AppBarState.Overview.OverviewAppBarState(
                    query = "",
                    focused = true,
                    enabled = true,
                    unfocus = false,
                    hideKeyboard = false
                )
            ),
            emptyFocusedDisabled =  CommonBodyState.AppBarState.Overview(
                value = CommonBodyState.AppBarState.Overview.OverviewAppBarState(
                    query = "",
                    focused = true,
                    enabled = false,
                    unfocus = false,
                    hideKeyboard = false
                )
            ),
        )
    }
}