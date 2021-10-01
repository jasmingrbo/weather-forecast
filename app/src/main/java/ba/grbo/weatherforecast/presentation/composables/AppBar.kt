package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable

@Composable
fun AppBar(
    body: @Composable () -> Unit,
) {
    Column {
        body()
        Divider()
    }
}