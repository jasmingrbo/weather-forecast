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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToLong

@HiltViewModel
class WeatherForecastViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(CommonBodyState.Default)
        private set

    fun onEvent(event: CommonBodyEvent) {
        when (event) {
            is OnQueryChange -> state = state.updateQuery(event.query)
            is OnFocusChanged -> {
                state = if (event.focused) state.updateFocusedToTrue()
                else state.updateFocusedAndUnfocusToFalse()
            }
            is OnEnabledChanged -> state = state.updateEnabled(event.enabled)
            is OnUpButtonClick -> when (event.body) {
                OVERVIEW -> {
                    viewModelScope.launch {
                        delay(90) // 375 the whole ripple animation
                        state = state.updateUnfocusToTrueAndResetQuery()
                        resetQuery(initialDelay = true)
                    }
                }
                DETAILS -> {
                }
                SETTINGS -> {
                }
            }
            is OnResetButtonClick -> viewModelScope.launch {
                delay(90)
                resetQuery()
            }
            is OnOverflowButtonClick -> {
            }
            is OnDoneImeActionPressed -> state = state.updateHideKeyboardToTrue()
            is OnSoftwareKeyboardHidden -> state = state.updateHideKeyboardToFalse()
        }
    }

    private suspend fun resetQuery(
        initialDelay: Boolean = false
    ) {
        val query = (state.appBarState as CommonBodyState.AppBarState.Overview).value.query
        val delayDuration = (150.0 / query.text.length).roundToLong()

        suspend fun updateQuery(query: TextFieldValue) {
            if (query.isEmpty()) state =  state.updateQuery(query)
            else {
                val temp = query.copy(text = query.text.substring(0 until query.text.lastIndex))
                state = state.updateQuery(temp)
                delay(delayDuration)
                updateQuery(temp)
            }
        }

        if (initialDelay) delay(delayDuration)
        updateQuery(query)
    }
}