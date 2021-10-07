package ba.grbo.weatherforecast.framework.mics

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import ba.grbo.weatherforecast.framework.mics.Constants.LocationSearcherExitAnimationDuration

@OptIn(ExperimentalAnimationApi::class)
fun customFadeIn(duration: Int = AnimationConstants.DefaultDurationMillis) = fadeIn(
    animationSpec = tween(
        durationMillis = duration,
        easing = LinearOutSlowInEasing
    )
)

@OptIn(ExperimentalAnimationApi::class)
fun customFadeOut(duration: Int = LocationSearcherExitAnimationDuration) = fadeOut(
    animationSpec = tween(durationMillis = duration, easing = FastOutLinearInEasing)
)

@OptIn(ExperimentalAnimationApi::class)
fun customSlideInVertically(fromBottom: Boolean) = slideInVertically(
    initialOffsetY = { height -> if (fromBottom) height else -height },
    animationSpec = tween(easing = LinearOutSlowInEasing)
)

@OptIn(ExperimentalAnimationApi::class)
fun customSlideOutVertically(toTop: Boolean) = slideOutVertically(
    targetOffsetY = { height -> if (toTop) -height else height },
    animationSpec = tween(
        durationMillis = LocationSearcherExitAnimationDuration,
        easing = FastOutLinearInEasing
    )
)