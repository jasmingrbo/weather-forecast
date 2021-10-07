package ba.grbo.weatherforecast.presentation.composables.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    /*Caution: Passing complex data structures over arguments is considered an anti-pattern.
    Each destination should be responsible for loading UI data based on the minimum necessary
    information, such as item IDs. This simplifies process recreation and avoids potential
    data inconsistencies.*/
    val id: Long = savedStateHandle.get<Long>("id")!!
}