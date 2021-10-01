package ba.grbo.weatherforecast.framework.data

import ba.grbo.core.domain.Constants
import ba.grbo.weatherforecast.framework.mics.EMPTY
import ba.grbo.weatherforecast.framework.mics.WHITESPACE

data class OverviewScreenState(
    val appBar: OverviewAppBarState,
    val internetBanner: InternetBannerState,
) {
    fun updateQuery(query: String) = copy(appBar = appBar.updateQuery(query))

    fun updateFocusedToTrue() = copy(appBar = appBar.updateFocused(true))

    fun updateEnabled(enabled: Boolean) = copy(appBar = appBar.updateEnabled(enabled))

    fun updateHideKeyboard(hideKeyboard: Boolean) = copy(
        appBar = appBar.updateHideKeyboard(hideKeyboard)
    )

    fun updateUnfocusToTrue() = copy(appBar = appBar.updateUnfocusToTrue())

    fun updateFocusedAndUnfocusToFalse() = copy(appBar = appBar.updateFocusedAndUnfocusToFalse())

    data class OverviewAppBarState(
        val query: String,
        val focused: Boolean,
        val enabled: Boolean,
        val unfocus: Boolean,
        val hideKeyboard: Boolean,
    ) {
        fun updateQuery(query: String) = copy(query = onQueryChange(query))

        fun updateFocused(focused: Boolean) = copy(focused = focused)

        fun updateEnabled(enabled: Boolean) = copy(enabled = enabled)

        fun updateHideKeyboard(hideKeyboard: Boolean) = copy(hideKeyboard = hideKeyboard)

        // Checking if focused to effectively disable multiple taps on UpButton, while
        // it's being slided and faded out, otherwise it will set unfocus to true, even
        // though the LocationSearcher is already unfocused, which will prevent from
        // unfocusing it when it gets the focus next time.
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

    data class InternetBannerState(val internet: Boolean) {
        companion object {
            val Default = InternetBannerState(internet = true)
        }
    }

    companion object {
        val Default = OverviewScreenState(
            appBar = OverviewAppBarState.Default,
            internetBanner = InternetBannerState.Default
        )
    }
}