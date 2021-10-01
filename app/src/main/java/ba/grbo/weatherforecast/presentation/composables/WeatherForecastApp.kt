package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@Composable
fun WeatherForecastApp() {
    WeatherForecastTheme {
        Surface {
            WeatherForecastNavHost()
        }
    }
}