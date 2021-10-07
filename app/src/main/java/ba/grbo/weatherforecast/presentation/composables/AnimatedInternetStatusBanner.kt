package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.core.domain.DEFAULT
import ba.grbo.weatherforecast.framework.mics.Constants.InternetStatusBannerExitAnimationDelay
import ba.grbo.weatherforecast.framework.mics.Constants.InternetStatusBannerExitAnimationDuration

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedInternetStatusBanner(internet: Boolean) {
    AnimatedVisibility(
        visible = !internet,
        enter = expandVertically(animationSpec = tween(easing = LinearOutSlowInEasing)),
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = InternetStatusBannerExitAnimationDuration,
                delayMillis = if (internet) InternetStatusBannerExitAnimationDelay else Int.DEFAULT,
                easing = FastOutLinearInEasing
            )
        )
    ) {
        InternetStatusBanner(internet = internet)
    }
}

@Preview(name = "WithInternet")
@Preview(
    name = "WithInternet",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedInternetStatusBannerWithInternetPreview() {
    Preview {
        AnimatedInternetStatusBanner(internet = true)
    }
}

@Preview(name = "WithoutInternet")
@Preview(
    name = "WithoutInternet",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedInternetStatusBannerWithoutInternetPreview() {
    Preview {
        AnimatedInternetStatusBanner(internet = false)
    }
}