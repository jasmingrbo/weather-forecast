package ba.grbo.weatherforecast.framework.mics

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

@OptIn(ExperimentalAnimationApi::class)
fun customFadeIn(duration: Int = AnimationConstants.DefaultDurationMillis) = fadeIn(
    animationSpec = tween(
        durationMillis = duration,
        easing = LinearOutSlowInEasing
    )
)

@OptIn(ExperimentalAnimationApi::class)
fun customFadeOut(duration: Int = 200) = fadeOut(
    animationSpec = tween(durationMillis = duration, easing = FastOutLinearInEasing)
)