package ba.grbo.weatherforecast.framework.mics

import androidx.compose.ui.text.input.TextFieldValue
import ba.grbo.weatherforecast.framework.data.CommonBodyState

object PreviewData {
    private const val QUERY = "Sarajevo"

    object OverviewAppBarState {
        val NonEmptyUnfocusedEnabled = CommonBodyState.AppBarState.Overview(
            value = CommonBodyState.AppBarState.Overview.OverviewAppBarState(
                query = Query.NonEmpty,
                focused = false,
                enabled = true,
                unfocus = false,
                hideKeyboard = false
            )
        )

        val NonEmptyUnfocusedDisabled = CommonBodyState.AppBarState.Overview(
            value = CommonBodyState.AppBarState.Overview.OverviewAppBarState(
                query = Query.NonEmpty,
                focused = false,
                enabled = false,
                unfocus = false,
                hideKeyboard = false
            )
        )
        val EmptyFocusedEnabled = CommonBodyState.AppBarState.Overview(
            value = CommonBodyState.AppBarState.Overview.OverviewAppBarState(
                query = Query.Empty,
                focused = true,
                enabled = true,
                unfocus = false,
                hideKeyboard = false
            )
        )
        val EmptyFocusedDisabled = CommonBodyState.AppBarState.Overview(
            value = CommonBodyState.AppBarState.Overview.OverviewAppBarState(
                query = Query.Empty,
                focused = true,
                enabled = false,
                unfocus = false,
                hideKeyboard = false
            )
        )
    }

    object Query {
        val NonEmpty = TextFieldValue(text = QUERY)
        val Empty = TextFieldValue(text = String.EMPTY)
    }
}