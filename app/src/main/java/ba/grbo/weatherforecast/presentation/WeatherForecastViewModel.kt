package ba.grbo.weatherforecast.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.grbo.weatherforecast.framework.data.CommonBodyEvent
import ba.grbo.weatherforecast.framework.data.CommonBodyEventHandler
import ba.grbo.weatherforecast.framework.data.CommonBodyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class WeatherForecastViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(CommonBodyState.Default)
        private set

    fun onEvent(event: CommonBodyEvent) = CommonBodyEventHandler(event, state)
        .onEach { state -> this.state = state }
        .launchIn(viewModelScope)
}