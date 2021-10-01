package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Screen(
    appBar: @Composable () -> Unit,
    body: @Composable () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        appBar()
        body()
    }
}