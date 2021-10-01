package ba.grbo.weatherforecast.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ba.grbo.weatherforecast.presentation.composables.WeatherForecastApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherForecastActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { WeatherForecastApp() }
    }
}