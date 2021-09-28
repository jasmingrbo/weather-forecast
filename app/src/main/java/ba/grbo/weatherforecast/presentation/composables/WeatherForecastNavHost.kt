package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.weatherforecast.framework.data.Body
import ba.grbo.weatherforecast.presentation.composables.bodies.DetailsBody
import ba.grbo.weatherforecast.presentation.composables.bodies.OverviewBody
import ba.grbo.weatherforecast.presentation.composables.bodies.SettingsBody

@Composable
fun WeatherForecastNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Body.OVERVIEW.route,
    ) {
        composable(Body.OVERVIEW.route) {
            OverviewBody()
        }
        composable(Body.DETAILS.route) {
            DetailsBody()
        }

        composable(Body.SETTINGS.route) {
            SettingsBody()
        }
    }
}