package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.framework.mics.customFadeIn
import ba.grbo.weatherforecast.framework.mics.customFadeOut
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedLeadingIcon(
    locationSearcherFocusedTransition: Transition<Boolean>,
    enabled: Boolean,
    onClick: () -> Unit
) {
    locationSearcherFocusedTransition.AnimatedContent(
        transitionSpec = { createLeadingIconTransitionSpec() },
        contentAlignment = Alignment.CenterStart
    ) { focused ->
        if (focused) UpButton(
            modifier = Modifier.padding(start = 0.dp, end = 8.dp),
            enabled = enabled,
            onClick = onClick
        ) else MagnifierIcon(
            modifier = Modifier.padding(start = 12.dp, end = 20.dp),
            enabled = enabled
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun AnimatedContentScope<Boolean>.createLeadingIconTransitionSpec() =
    if (targetState) customSlideInVertically(fromBottom = true) + customFadeIn() with
            customSlideOutVertically(toTop = true) + customFadeOut()
    else customSlideInVertically(fromBottom = false) + customFadeIn() with
            customSlideOutVertically(toTop = false) + customFadeOut()


@OptIn(ExperimentalAnimationApi::class)
private fun customSlideInVertically(fromBottom: Boolean) = slideInVertically(
    initialOffsetY = { height -> if (fromBottom) height else -height },
    animationSpec = tween(easing = LinearOutSlowInEasing)
)

@OptIn(ExperimentalAnimationApi::class)
private fun customSlideOutVertically(toTop: Boolean) = slideOutVertically(
    targetOffsetY = { height -> if (toTop) -height else height },
    animationSpec = tween(durationMillis = 200, easing = FastOutLinearInEasing)
)

@Preview(
    name = "Light-UnfocusedEnabled",
    showBackground = true
)
@Preview(
    name = "Dark-UnfocusedEnabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintUnfocusedEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedLeadingIcon(
                locationSearcherFocusedTransition = updateTransition(targetState = false, label = ""),
                enabled = true,
                onClick = {}
            )
        }
    }
}

@Preview(
    name = "Light-UnfocusedDisabled",
    showBackground = true
)
@Preview(
    name = "Dark-UnfocusedDisabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintUnfocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedLeadingIcon(
                locationSearcherFocusedTransition = updateTransition(targetState = false, label = ""),
                enabled = false,
                onClick = {}
            )
        }
    }
}

@Preview(
    name = "Light-FocusedEnabled",
    showBackground = true
)
@Preview(
    name = "Dark-FocusedEnabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintFocusedEnabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedLeadingIcon(
                locationSearcherFocusedTransition = updateTransition(targetState = true, label = ""),
                enabled = true,
                onClick = {}
            )
        }
    }
}

@Preview(
    name = "Light-FocusedDisabled",
    showBackground = true
)
@Preview(
    name = "Dark-FocusedDisabled",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintFocusedDisabledPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedLeadingIcon(
                locationSearcherFocusedTransition = updateTransition(targetState = true, label = ""),
                enabled = false,
                onClick = {}
            )
        }
    }
}