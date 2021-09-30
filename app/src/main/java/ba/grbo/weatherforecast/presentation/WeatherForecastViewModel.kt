package ba.grbo.weatherforecast.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.grbo.weatherforecast.framework.data.Body.DETAILS
import ba.grbo.weatherforecast.framework.data.Body.OVERVIEW
import ba.grbo.weatherforecast.framework.data.Body.SETTINGS
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnDoneImeActionPressed
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnEnabledChanged
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnFocusChanged
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnOverflowButtonClick
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnQueryChange
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnResetButtonClick
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnSoftwareKeyboardHidden
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnUpButtonClick
import ba.grbo.weatherforecast.framework.data.CommonBodyState
import ba.grbo.weatherforecast.framework.mics.isEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToLong

@HiltViewModel
class WeatherForecastViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(CommonBodyState.Default)
        private set

    private var resetQueryJob: Job? = null

    // to prevent updating query's composition when cursor not at the end and we start resetting
    // the query
    private var canUpdateQuery = true

    fun onEvent(event: CommonBodyEvent) {
        when (event) {
            is OnQueryChange -> {
                if (canUpdateQuery) {
                    resetQueryJob?.cancel()
                    state = state.updateQuery(event.query)
                }
            }
            is OnFocusChanged -> {
                state = if (event.focused) {
                    resetQueryJob?.cancel()
                    state.updateFocusedToTrue()
                } else state.updateFocusedAndUnfocusToFalse()
            }
            is OnEnabledChanged -> state = state.updateEnabled(event.enabled)
            is OnUpButtonClick -> when (event.body) {
                OVERVIEW -> {
                    resetQueryJob = viewModelScope.launch {
                        canUpdateQuery = false
                        delay(90) // 375 the whole ripple animation
                        state = state.updateUnfocusToTrueAndResetQuery()
                        resetQuery(initialDelay = true)
                        canUpdateQuery = true
                    }
                }
                DETAILS -> {
                }
                SETTINGS -> {
                }
            }
            is OnResetButtonClick -> {
                resetQueryJob = viewModelScope.launch {
                    delay(90)
                    resetQuery()
                }
            }
            is OnOverflowButtonClick -> {
            }
            is OnDoneImeActionPressed -> state = state.updateHideKeyboardToTrue()
            is OnSoftwareKeyboardHidden -> state = state.updateHideKeyboardToFalse()
        }
    }

    private suspend fun resetQuery(
        duration: Double = 150.0,
        initialDelay: Boolean = false
    ) {
        val query = (state.appBarState as CommonBodyState.AppBarState.Overview).value.query
        if (query.isEmpty()) return

        val delayDuration = (duration / query.text.length).roundToLong()

        suspend fun updateQuery(query: TextFieldValue) {
            if (query.isEmpty()) state = state.updateQuery(query)
            else {
                val temp = query.copy(text = query.text.substring(0 until query.text.lastIndex))
                state = state.updateQuery(temp)
                delay(delayDuration)
                // Don't have to check for coroutineContext.isActive, because we are calling
                // delay, which is a library function, and all library functions are cancellable
                // by default, that is they check whether or not a job is in active state
                updateQuery(temp)
            }
        }

        if (initialDelay) delay(delayDuration)
        updateQuery(query)
    }
}