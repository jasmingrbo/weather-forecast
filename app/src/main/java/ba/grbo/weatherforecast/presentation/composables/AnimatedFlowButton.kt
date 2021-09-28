package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.mics.customFadeIn
import ba.grbo.weatherforecast.framework.mics.customFadeOut
import ba.grbo.weatherforecast.framework.theme.WeatherForecastTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedOverflowButton(
    modifier: Modifier = Modifier,
    locationSearcherFocusedTransition: Transition<Boolean>,
    onClick: () -> Unit
) {
    locationSearcherFocusedTransition.AnimatedVisibility(
        visible = { isFocused -> !isFocused },
        enter = customFadeIn() + expandHorizontally(
            expandFrom = Alignment.Start,
            animationSpec = tween(easing = LinearOutSlowInEasing)
        ),
        exit = customFadeOut() + shrinkHorizontally(
            shrinkTowards = Alignment.Start,
            animationSpec = tween(durationMillis = 200, easing = FastOutLinearInEasing)
        )
    ) {
        OverflowButton(
            modifier = modifier,
            onClick = onClick
        )
    }
}

@Preview(
    name = "Light-Unfocused",
    showBackground = true
)
@Preview(
    name = "Dark-Unfocused",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedOverflowButtonUnfocusedPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedOverflowButton(
                locationSearcherFocusedTransition = updateTransition(
                    targetState = false, // location searcher is unfocused
                    label = ""
                ),
                onClick = {}
            )
        }
    }
}

@Preview(
    name = "Light-Focused",
    showBackground = true
)
@Preview(
    name = "Dark-Focused",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedOverflowButtonFocusedPreview() {
    WeatherForecastTheme {
        Surface {
            AnimatedOverflowButton(
                locationSearcherFocusedTransition = updateTransition(
                    targetState = true, // location searcher is focused
                    label = ""
                ),
                onClick = {}
            )
        }
    }
}