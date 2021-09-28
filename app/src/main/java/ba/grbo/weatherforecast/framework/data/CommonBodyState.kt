package ba.grbo.weatherforecast.framework.data

data class CommonBodyState(
    val appBarState: AppBarState,
    val internetAvailabilityBannerState: InternetAvailabilityBannerState
) {
    fun updateQuery(query: String) = copy(
        appBarState = (appBarState as AppBarState.Overview).copy(
            value = appBarState.value.copy(query = query)
        )
    )

    fun updateFocusedToTrue() = copy(
        appBarState = (appBarState as AppBarState.Overview).copy(
            value = appBarState.value.copy(focused = true)
        )
    )

    fun updateEnabled(enabled: Boolean) = copy(
        appBarState = (appBarState as AppBarState.Overview).copy(
            value = appBarState.value.copy(enabled = enabled)
        )
    )

    fun updateUnfocusToTrue() = copy(
        appBarState = (appBarState as AppBarState.Overview).copy(
            value = appBarState.value.copy(unfocus = true)
        )
    )

    fun updateHideKeyboardToTrue() = updateHideKeyboard(true)

    fun updateHideKeyboardToFalse() = updateHideKeyboard(false)

    fun updateFocusedAndUnfocusToFalse() = copy(
        appBarState = (appBarState as AppBarState.Overview).copy(
            value = appBarState.value.copy(
                focused = false,
                unfocus = false,
            )
        )
    )

    private fun updateHideKeyboard(hideKeyboard: Boolean) = copy(
        appBarState = (appBarState as AppBarState.Overview).copy(
            value = appBarState.value.copy(hideKeyboard = hideKeyboard)
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
                companion object {
                    val Default = OverviewAppBarState(
                        query = "",
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

