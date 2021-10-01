package ba.grbo.weatherforecast.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent
import ba.grbo.weatherforecast.framework.data.OverviewScreenEventHandler
import ba.grbo.weatherforecast.framework.data.OverviewScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class OverviewScreenViewModel @Inject constructor(
    private val eventHandler: OverviewScreenEventHandler,
) : ViewModel() {
    var state by mutableStateOf(OverviewScreenState.Default)
        private set

    fun onEvent(event: OverviewScreenEvent) = eventHandler(event, state)
        .onEach { state -> this.state = state }
        .launchIn(viewModelScope)
}