package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun <T> SimpleListItem(
    item: T,
    clickable: Boolean,
    text: String,
    divider: Boolean = true,
    onClick: (T) -> Unit,
    icon: @Composable () -> Unit,
) {
    Card(shape = RectangleShape){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = clickable) { onClick(item) }
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon()
                Text(text = text)
            }
            if (divider) Divider(thickness = 0.5.dp)
        }
    }
}
