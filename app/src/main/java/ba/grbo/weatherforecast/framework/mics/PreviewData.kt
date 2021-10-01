package ba.grbo.weatherforecast.framework.mics

import ba.grbo.weatherforecast.framework.data.OverviewScreenState
import ba.grbo.weatherforecast.framework.data.OverviewScreenState.InternetBannerState
import ba.grbo.weatherforecast.framework.data.OverviewScreenState.OverviewAppBarState

object PreviewData {
    private const val QUERY = "Sarajevo"

    // When done, add more states
    object OverviewScreenState {
        val NonEmptyUnfocusedEnabledWithInternet = OverviewScreenState(
            appBar = OverviewAppBarState.NonEmptyUnfocusedEnabled,
            internetBanner = InternetBannerState.WithInternet
        )

        val EmptyFocusedEnabledWithoutInternet = OverviewScreenState(
            appBar = OverviewAppBarState.EmptyFocusedEnabled,
            internetBanner = InternetBannerState.WithoutInternet
        )
    }

    object OverviewAppBarState {
        val NonEmptyUnfocusedEnabled = OverviewAppBarState(
            query = Query.NonEmpty,
            focused = false,
            enabled = true,
            unfocus = false,
            hideKeyboard = false
        )

        val NonEmptyUnfocusedDisabled = OverviewAppBarState(
            query = Query.NonEmpty,
            focused = false,
            enabled = false,
            unfocus = false,
            hideKeyboard = false
        )
        val EmptyFocusedEnabled = OverviewAppBarState(
            query = Query.Empty,
            focused = true,
            enabled = true,
            unfocus = false,
            hideKeyboard = false
        )
        val EmptyFocusedDisabled = OverviewAppBarState(
            query = Query.Empty,
            focused = true,
            enabled = false,
            unfocus = false,
            hideKeyboard = false
        )
    }

    object InternetBannerState {
        val WithInternet = InternetBannerState(internet = true)
        val WithoutInternet = InternetBannerState(internet = false)
    }

    object Query {
        const val NonEmpty = QUERY
        val Empty = String.EMPTY
    }
}