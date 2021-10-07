package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.core.domain.DEFAULT
import ba.grbo.weatherforecast.framework.mics.Constants.LocationSearcherExitAnimationDuration
import ba.grbo.weatherforecast.framework.mics.customFadeIn
import ba.grbo.weatherforecast.framework.mics.customFadeOut

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedOverflowButton(
    modifier: Modifier = Modifier,
    locationSearcherFocusedTransition: Transition<Boolean>,
    onClick: () -> Unit
) {
    locationSearcherFocusedTransition.AnimatedVisibility(
        visible = { focused -> !focused },
        enter = customFadeIn() + expandHorizontally(
            expandFrom = Alignment.Start,
            animationSpec = tween(easing = LinearOutSlowInEasing)
        ),
        exit = customFadeOut() + shrinkHorizontally(
            shrinkTowards = Alignment.Start,
            animationSpec = tween(
                durationMillis = LocationSearcherExitAnimationDuration,
                easing = FastOutLinearInEasing
            )
        )
    ) {
        OverflowButton(
            modifier = modifier,
            onClick = onClick
        )
    }
}

@Preview(name = "Unfocused")
@Preview(
    name = "Unfocused",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedOverflowButtonUnfocusedPreview() {
    Preview {
        AnimatedOverflowButton(
            locationSearcherFocusedTransition = updateTransition(
                targetState = false, // location searcher is unfocused
                label = String.DEFAULT
            ),
            onClick = {}
        )
    }
}

@Preview(name = "Focused")
@Preview(
    name = "Focused",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedOverflowButtonFocusedPreview() {
    Preview {
        AnimatedOverflowButton(
            locationSearcherFocusedTransition = updateTransition(
                targetState = true, // location searcher is focused
                label = String.DEFAULT
            ),
            onClick = {}
        )
    }
}