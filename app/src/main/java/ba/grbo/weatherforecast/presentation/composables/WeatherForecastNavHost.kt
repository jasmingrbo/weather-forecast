package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.weatherforecast.framework.data.Screen
import ba.grbo.weatherforecast.presentation.OverviewScreenViewModel
import ba.grbo.weatherforecast.presentation.composables.screens.DetailsScreen
import ba.grbo.weatherforecast.presentation.composables.screens.OverviewScreen
import ba.grbo.weatherforecast.presentation.composables.screens.SettingsScreen

@Composable
fun WeatherForecastNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.OVERVIEW.route,
    ) {
        composable(Screen.OVERVIEW.route) {
            val viewModel = hiltViewModel<OverviewScreenViewModel>()
            OverviewScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent
            )
        }
        composable(Screen.DETAILS.route) {
            DetailsScreen()
        }

        composable(Screen.SETTINGS.route) {
            SettingsScreen()
        }
    }
}