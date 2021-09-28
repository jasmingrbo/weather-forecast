package ba.grbo.weatherforecast.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.grbo.weatherforecast.framework.data.Body.DETAILS
import ba.grbo.weatherforecast.framework.data.Body.OVERVIEW
import ba.grbo.weatherforecast.framework.data.Body.SETTINGS
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnDoneImeAction
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnEnabledChanged
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnFocusChanged
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnOverflowButtonClick
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnQueryChange
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnResetButtonClick
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnSoftwareKeyboardHidden
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent.OnUpButtonClick
import ba.grbo.weatherforecast.framework.data.CommonBodyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

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
                OVERVIEW -> state = state.updateUnfocusToTrue()
                DETAILS -> {}
                SETTINGS -> {}
            }
            is OnResetButtonClick -> viewModelScope.launch {
                delay(90)
                state = state.updateQuery("")
            }
            is OnOverflowButtonClick -> {
            }
            is OnDoneImeAction -> state = state.updateHideKeyboardToTrue()
            is OnSoftwareKeyboardHidden -> state = state.updateHideKeyboardToFalse()
        }
    }
}