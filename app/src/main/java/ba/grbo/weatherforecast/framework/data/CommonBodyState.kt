package ba.grbo.weatherforecast.framework.data

import androidx.compose.ui.text.input.TextFieldValue
import ba.grbo.core.domain.Constants
import ba.grbo.weatherforecast.framework.data.CommonBodyState.AppBarState.Overview.OverviewAppBarState
import ba.grbo.weatherforecast.framework.mics.Default
import ba.grbo.weatherforecast.framework.mics.EMPTY
import ba.grbo.weatherforecast.framework.mics.WHITESPACE
import ba.grbo.weatherforecast.framework.mics.removeLastChar
import ba.grbo.weatherforecast.framework.mics.updateText

data class CommonBodyState(
    val appBarState: AppBarState,
    val internetAvailabilityBannerState: InternetAvailabilityBannerState
) {
    fun updateQuery(query: TextFieldValue) = updateAppBarState { it.updateQuery(query) }

    fun updateFocusedToTrue() = updateAppBarState { it.updateFocused(true) }

    fun updateEnabled(enabled: Boolean) = updateAppBarState { it.updateEnabled(enabled) }

    fun updateHideKeyboardToTrue() = updateAppBarState { it.updateHideKeyboard(true) }

    fun updateHideKeyboardToFalse() = updateAppBarState { it.updateHideKeyboard(false) }

    fun updateUnfocusToTrueAndResetQuery() = updateAppBarState {
        it.updateUnfocusToTrueAndResetQuery()
    }

    fun updateFocusedAndUnfocusToFalse() = updateAppBarState { it.updateFocusedAndUnfocusToFalse() }

    private fun updateAppBarState(action: (OverviewAppBarState) -> OverviewAppBarState) = copy(
        appBarState = AppBarState.Overview(
            value = action((appBarState as AppBarState.Overview).value)
        )
    )

    sealed class AppBarState {
        data class Overview(val value: OverviewAppBarState) : AppBarState() {
            data class OverviewAppBarState(
                val query: TextFieldValue,
                val focused: Boolean,
                val enabled: Boolean,
                val unfocus: Boolean,
                val hideKeyboard: Boolean
            ) {
                fun updateQuery(query: TextFieldValue) = copy(
                    query = query.updateText(onQueryChange(query.text))
                )

                fun updateFocused(focused: Boolean) = copy(focused = focused)

                fun updateEnabled(enabled: Boolean) = copy(enabled = enabled)

                fun updateHideKeyboard(hideKeyboard: Boolean) = copy(hideKeyboard = hideKeyboard)

                fun updateUnfocusToTrueAndResetQuery() = copy(
                    unfocus = true, query = query.copy(text = query.text.removeLastChar())
                )

                fun updateFocusedAndUnfocusToFalse() = copy(focused = false, unfocus = false)

                private fun onQueryChange(query: String) = when {
                    query.isBlank() -> String.EMPTY
                    else -> removeWhitespace(
                        if (query.length <= Constants.LOCATION_SEARCHER_LENGTH_LIMIT) query
                        else query.substring(0, Constants.LOCATION_SEARCHER_LENGTH_LIMIT)
                    )
                }

                private fun removeWhitespace(input: String): String {
                    val trimmed = input.trim()
                        .split(Char.WHITESPACE)
                        .filter { it.isNotEmpty() }
                        .joinToString(String.WHITESPACE)

                    return if (input.last() == Char.WHITESPACE) "$trimmed " else trimmed
                }

                companion object {
                    val Default = OverviewAppBarState(
                        query = TextFieldValue.Default,
                        focused = false,
                        enabled = true,
                        unfocus = false,
                        hideKeyboard = false
                    )
                }
            }
        }

        data class Details(val value: DetailsAppBarState) : AppBarState() {
            data class DetailsAppBarState(
                val flagResource: Int,
                val placeName: String
            )
        }

        data class Settings(val value: String) : AppBarState()

        companion object {
            val Default = Overview(value = Overview.OverviewAppBarState.Default)
        }
    }

    data class InternetAvailabilityBannerState(val hasInternet: Boolean) {
        companion object {
            val Default = InternetAvailabilityBannerState(hasInternet = true)
        }
    }

    companion object {
        val Default = CommonBodyState(
            appBarState = AppBarState.Default,
            internetAvailabilityBannerState = InternetAvailabilityBannerState.Default
        )
    }
}