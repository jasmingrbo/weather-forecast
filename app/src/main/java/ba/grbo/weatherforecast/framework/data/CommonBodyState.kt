package ba.grbo.weatherforecast.framework.data

import ba.grbo.core.domain.Constants
import ba.grbo.weatherforecast.framework.data.CommonBodyState.AppBarState.Overview.OverviewAppBarState
import ba.grbo.weatherforecast.framework.mics.EMPTY
import ba.grbo.weatherforecast.framework.mics.WHITESPACE

data class CommonBodyState(
    val appBarState: AppBarState,
    val internetAvailabilityBannerState: InternetAvailabilityBannerState
) {
    fun updateQuery(query: String) = updateAppBarState { it.updateQuery(query) }

    fun updateFocusedToTrue() = updateAppBarState { it.updateFocused(true) }

    fun updateEnabled(enabled: Boolean) = updateAppBarState { it.updateEnabled(enabled) }

    fun updateHideKeyboard(hideKeyboard: Boolean) = updateAppBarState {
        it.updateHideKeyboard(hideKeyboard)
    }

    fun updateUnfocusToTrue() = updateAppBarState {
        it.updateUnfocusToTrue()
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
                val query: String,
                val focused: Boolean,
                val enabled: Boolean,
                val unfocus: Boolean,
                val hideKeyboard: Boolean
            ) {
                fun updateQuery(query: String) = copy(query = onQueryChange(query))

                fun updateFocused(focused: Boolean) = copy(focused = focused)

                fun updateEnabled(enabled: Boolean) = copy(enabled = enabled)

                fun updateHideKeyboard(hideKeyboard: Boolean) = copy(hideKeyboard = hideKeyboard)

                // Checking if focused to effectively disable multiple taps on UpButton, while
                // it's being slided and faded out, otherwise it will set unfocus to true, even
                // though the LocationSearcher is already unfocused, which will prevent from
                // unfocusing it when it gets the focus next time
                fun updateUnfocusToTrue() = if (focused) copy(unfocus = true) else this

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
                        query = String.EMPTY,
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