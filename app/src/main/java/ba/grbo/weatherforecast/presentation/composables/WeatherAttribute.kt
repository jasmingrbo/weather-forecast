package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun WeatherAttribute(content: @Composable () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        content()
    }
}