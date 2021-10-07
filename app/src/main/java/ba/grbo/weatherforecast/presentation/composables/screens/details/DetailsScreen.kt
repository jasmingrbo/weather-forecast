package ba.grbo.weatherforecast.presentation.composables.screens.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailsScreen(id: Long) {
    Text(text = "Place with an id: $id clicked.")
}