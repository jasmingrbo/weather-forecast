package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.R
import coil.compose.rememberImagePainter

@Composable
fun RowScope.WeatherIconAndDescription(
    icon: String,
    description: String
) {
    Image(
        painter = rememberImagePainter(data = "https://openweathermap.org/img/wn/$icon@2x.png"),
        contentDescription = stringResource(id = R.string.weather_icon_description),
        modifier = Modifier.size(72.dp)
    )

    Text(
        modifier = Modifier.weight(1f),
        text = description,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Preview(name = "Sunny")
@Preview(
    name = "Sunny",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun WeatherIconAndDescriptionSunnyPreview() {
    Preview {
        Row(verticalAlignment = Alignment.CenterVertically) {
           WeatherIconAndDescription(
               icon = "01d",
               description = "Sunny"
           )
        }
    }
}