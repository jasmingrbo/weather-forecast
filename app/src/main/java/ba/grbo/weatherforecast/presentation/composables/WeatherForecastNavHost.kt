package ba.grbo.weatherforecast.presentation.composables

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.InternetStatusChanged
import ba.grbo.weatherforecast.framework.data.states.Screen.DETAILS
import ba.grbo.weatherforecast.framework.data.states.Screen.OVERVIEW
import ba.grbo.weatherforecast.framework.data.states.Screen.SETTINGS
import ba.grbo.weatherforecast.presentation.composables.screens.details.DetailsScreen
import ba.grbo.weatherforecast.presentation.composables.screens.SettingsScreen
import ba.grbo.weatherforecast.presentation.composables.screens.details.DetailsScreenViewModel
import ba.grbo.weatherforecast.presentation.composables.screens.overview.OverviewScreen
import ba.grbo.weatherforecast.presentation.composables.screens.overview.OverviewScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WeatherForecastNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = OVERVIEW.route,
    ) {
        composable(OVERVIEW.route) {
            val viewModel = hiltViewModel<OverviewScreenViewModel>().apply {
                internetStatus.collect(onValueChanged = { internet ->
                    onEvent(InternetStatusChanged(internet))
                })
            }
            OverviewScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onPlaceClicked = { id -> navController.navigate("${DETAILS.route}/$id") }
            )
            // onSettingsButtonClicked = { navController.navigate(SETTINGS.route) }
        }
        composable(
            route = "${DETAILS.route}/{id}",
            arguments = listOf(navArgument(name = "id") { type = NavType.LongType })
        ) {
            val viewModel = hiltViewModel<DetailsScreenViewModel>()
            DetailsScreen(id = viewModel.id)
            // onUpButtonClicked =  { navController.navigateUp()}
        }

        composable(SETTINGS.route) {
            SettingsScreen()
            // onUpButtonClicked =  { navController.navigateUp()}
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun <T : R, R> StateFlow<T>.collect(onValueChanged: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            collectLatest {
                // Delaying for smooth transition from wifi to cellular, to avoid showing no
                // connection for a brief moment.
                delay(1000)
                onValueChanged(it)
            }
        }
    }
}

@Composable
fun keyboardAsState(): State<Boolean> {
    val keyboardState = remember { mutableStateOf(false) }
    val view = LocalView.current

    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = keypadHeight > screenHeight * 0.15
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose { view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener) }
    }

    return keyboardState
}
