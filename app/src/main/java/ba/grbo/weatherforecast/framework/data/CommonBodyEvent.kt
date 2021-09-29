package ba.grbo.weatherforecast.framework.data

import androidx.compose.ui.text.input.TextFieldValue

sealed class CommonBodyEvent {
    class OnQueryChange(val query: TextFieldValue) : CommonBodyEvent()
    class OnFocusChanged(val focused: Boolean) : CommonBodyEvent()
    class OnEnabledChanged(val enabled: Boolean) : CommonBodyEvent()
    class OnUpButtonClick(val body: Body) : CommonBodyEvent()
    object OnResetButtonClick : CommonBodyEvent()
    object OnOverflowButtonClick : CommonBodyEvent()
    object OnDoneImeActionPressed: CommonBodyEvent()
    object OnSoftwareKeyboardHidden: CommonBodyEvent()
}