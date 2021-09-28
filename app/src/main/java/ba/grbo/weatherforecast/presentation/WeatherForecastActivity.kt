package ba.grbo.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.grbo.weatherforecast.WeatherForecastScreen.DETAILS
import ba.grbo.weatherforecast.WeatherForecastScreen.OVERVIEW
import ba.grbo.weatherforecast.WeatherForecastScreen.SETTINGS
import ba.grbo.weatherforecast.presentation.composables.bodies.CommonBody
import ba.grbo.weatherforecast.presentation.composables.bodies.DetailsBody
import ba.grbo.weatherforecast.presentation.composables.bodies.OverviewBody
import ba.grbo.weatherforecast.presentation.composables.bodies.SettingsBody
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { WeatherForecastApp(onScreenChange = {}) }
    }
}

sealed class AppBarState {
    data class Overview(val value: OverviewAppBarState) : AppBarState()
    data class Details(val value: DetailsAppBarState) : AppBarState()
    data class Settings(val value: String) : AppBarState()
}

data class CommonBodyState(
    val appBarState: AppBarState,
    val internetAvailabilityBannerState: InternetAvailabilityBannerState
)

data class InternetAvailabilityBannerState(val hasInternet: Boolean)

data class OverviewAppBarState(val query: String, val isFocused: Boolean, val isEnabled: Boolean)
data class DetailsAppBarState(val flagResource: Int, val placeName: String)

sealed interface AppBarCallables {
    interface OverviewAppBarCallables : AppBarCallables {
        val onQueryChange: (String) -> Unit
        val onFocusChange: (Boolean) -> Unit
        val onUpClicked: () -> Unit
        val onResetClicked: () -> Unit
        val onOverflowClicked: () -> Unit
    }

    interface DetailsAppBarCallables : AppBarCallables {
        val onBackClicked: () -> Unit
    }

    interface SettingsAppBarCallables : AppBarCallables {
        val onBackClicked: () -> Unit
    }
}

enum class WeatherForecastScreen(val route: String) {
    OVERVIEW("overview"),
    DETAILS("details"),
    SETTINGS("settings");

    companion object {
        fun fromRoute(route: String?) = when (route) {
            OVERVIEW.route -> OVERVIEW
            DETAILS.route -> DETAILS
            SETTINGS.route -> SETTINGS
            else -> throw IllegalArgumentException("Unknown route: $route")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherForecastApp(
    onScreenChange: (WeatherForecastScreen) -> Unit
) {
    WeatherForecastTheme {
        Surface {
            val (query, setQuery) = remember { mutableStateOf("") }
            val (isEnabled, setIsEnabled) = remember { mutableStateOf(true) }
            val (isFocused, setIsFocused) = remember { mutableStateOf(false) }
            val focusManager = LocalFocusManager.current
            // val clearFocus = { focusManager.clearFocus(true) }

            val scope = rememberCoroutineScope()

            val DefaultOverviewAppBarCallables = object : AppBarCallables.OverviewAppBarCallables {
                override val onQueryChange: (String) -> Unit = setQuery
                override val onFocusChange: (Boolean) -> Unit = setIsFocused
                override val onUpClicked: () -> Unit = {
                    scope.launch {
                        delay(90) //375 the whole ripple animation
                        focusManager.clearFocus(true)
                    }
                }
                override val onResetClicked: () -> Unit = {
                    scope.launch {
                        delay(90)
                        setQuery("")
                    }
                }
                override val onOverflowClicked: () -> Unit = {}
            }

            val navController = rememberNavController()
            // val backstackEntry = navController.currentBackStackEntryAsState()
            // LaunchedEffect(backstackEntry.value?.destination?.route) {
            //     val screen = WeatherForecastScreen.fromRoute(
            //         backstackEntry.value?.destination?.route
            //     )
            //     onScreenChange(screen)
            // }
            Column {
                CommonBody(
                    state = CommonBodyState(
                        appBarState = AppBarState.Overview(
                            value = OverviewAppBarState(
                                query = query,
                                isFocused = isFocused,
                                isEnabled = isEnabled
                            )
                        ),
                        internetAvailabilityBannerState = InternetAvailabilityBannerState(true)
                    ),
                    callables = DefaultOverviewAppBarCallables
                )
                WeatherForecastNavHost(navController = navController)
            }
        }
    }
}

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
            OverviewBody()
        }
        composable(DETAILS.route) {
            DetailsBody()
        }

        composable(SETTINGS.route) {
            SettingsBody()
        }
    }
}