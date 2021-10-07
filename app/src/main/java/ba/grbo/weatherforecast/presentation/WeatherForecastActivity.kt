package ba.grbo.weatherforecast.presentation

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ba.grbo.weatherforecast.R
import ba.grbo.weatherforecast.presentation.composables.WeatherForecastApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherForecastActivity : ComponentActivity() {
    private val rootView: View
        get() = findViewById<View>(android.R.id.content).rootView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_WeatherForecast)
        super.onCreate(savedInstanceState)
        setContent { WeatherForecastApp() }
    }

    // Fixes background behind the keyboard when the ui mode changes
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setRootViewBackgroundColor(getUiMode(newConfig))
    }

    private fun getUiMode(configuration: Configuration) =
        configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

    private fun setRootViewBackgroundColor(uiMode: Int) {
        when (uiMode) {
            UI_MODE_NIGHT_YES -> rootView.setBackgroundColor(0xF191919)
            UI_MODE_NIGHT_NO -> rootView.setBackgroundColor(0xFF9F9F9)
        }
    }
}