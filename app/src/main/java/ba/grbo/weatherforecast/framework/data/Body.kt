package ba.grbo.weatherforecast.framework.data

enum class Body(val route: String) {
    OVERVIEW("overview"),
    DETAILS("details"),
    SETTINGS("settings");

    // companion object {
    //     fun fromRoute(route: String?) = when (route) {
    //         OVERVIEW.route -> OVERVIEW
    //         DETAILS.route -> DETAILS
    //         SETTINGS.route -> SETTINGS
    //         else -> throw IllegalArgumentException("Unknown route: $route")
    //     }
    // }
}