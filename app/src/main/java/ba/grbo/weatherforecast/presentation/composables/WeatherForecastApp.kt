package ba.grbo.weatherforecast.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.framework.theme.DarkSystemBarsColor
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WeatherForecastApp() {
    WeatherForecastTheme {
        Surface {
            setSystemBarColor()
            WeatherForecastNavHost()
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
private fun setSystemBarColor() {
    val systemUiController = rememberSystemUiController()
    val darkTheme = !MaterialTheme.colors.isLight
    val color = LocalElevationOverlay.current?.apply(DarkSystemBarsColor, 3.dp)!!
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (darkTheme) color else Color.White,
            darkIcons = !darkTheme
        )
    }
}