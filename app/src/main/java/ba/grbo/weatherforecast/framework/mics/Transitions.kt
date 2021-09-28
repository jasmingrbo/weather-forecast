package ba.grbo.weatherforecast.framework.mics

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

@OptIn(ExperimentalAnimationApi::class)
fun customFadeIn() = fadeIn(animationSpec = tween(easing = LinearOutSlowInEasing))

@OptIn(ExperimentalAnimationApi::class)
fun customFadeOut() = fadeOut(
    animationSpec = tween(durationMillis = 200, easing = FastOutLinearInEasing)
)