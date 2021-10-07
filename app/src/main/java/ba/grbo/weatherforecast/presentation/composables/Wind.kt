package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.R

@Composable
fun Wind(
    text: String,
    rotationAngle: Float
) {
    WeatherAttribute {
        TypicalWeatherAttributeContent(
            imageId = R.drawable.ic_wind,
            imageDescription = R.string.wind_description,
            text = text
        )
        Spacer(modifier = Modifier.width(if (rotationAngle in (0.01..179.99)) 6.dp else 2.dp))
        Image(
            modifier = Modifier
                .rotate(rotationAngle)
                .size(16.dp),
            painter = painterResource(id = R.drawable.ic_wind_vane),
            contentDescription = stringResource(id = R.string.wind_vane_description)
        )
    }
}

@Preview(name = "1.5m/s")
@Preview(
    name = "1.5m/s",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun Wind1_5Preview() {
    Preview {
        Wind(
            text = "1.5m/s SW",
            rotationAngle = 150f
        )
    }
}

@Preview(name = "3.2m/s")
@Preview(
    name = "3.2m/s",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun Wind3_2Preview() {
    Preview {
        Wind(
            text = "3.2m/s NE",
            rotationAngle = 37f
        )
    }
}
