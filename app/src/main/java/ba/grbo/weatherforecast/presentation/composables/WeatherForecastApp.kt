package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme
import ba.grbo.weatherforecast.presentation.WeatherForecastViewModel
import ba.grbo.weatherforecast.presentation.composables.bodies.CommonBody

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherForecastApp(viewModel: WeatherForecastViewModel) {
    WeatherForecastTheme {
        Surface {
            val navController = rememberNavController()

            Column {
                CommonBody(
                    state = viewModel.state,
                    onEvent = viewModel::onEvent
                )
                WeatherForecastNavHost(navController = navController)
            }
        }
    }
}

// Add previews