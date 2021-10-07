package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.with
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.core.domain.DEFAULT
import ba.grbo.weatherforecast.framework.mics.customFadeIn
import ba.grbo.weatherforecast.framework.mics.customFadeOut
import ba.grbo.weatherforecast.framework.mics.customSlideInVertically
import ba.grbo.weatherforecast.framework.mics.customSlideOutVertically

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

@Preview(name = "UnfocusedEnabled")
@Preview(
    name = "UnfocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedLeadingIconUnfocusedEnabledPreview() {
    Preview {
        AnimatedLeadingIcon(
            locationSearcherFocusedTransition = updateTransition(
                targetState = false,
                label = String.DEFAULT
            ),
            enabled = true,
            onClick = {}
        )
    }
}

@Preview(name = "UnfocusedDisabled")
@Preview(
    name = "UnfocusedDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedLeadingIconUnfocusedDisabledPreview() {
    Preview {
        AnimatedLeadingIcon(
            locationSearcherFocusedTransition = updateTransition(
                targetState = false,
                label = String.DEFAULT
            ),
            enabled = false,
            onClick = {}
        )
    }
}

@Preview(name = "FocusedEnabled")
@Preview(
    name = "FocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedLeadingIconFocusedEnabledPreview() {
    Preview {
        AnimatedLeadingIcon(
            locationSearcherFocusedTransition = updateTransition(
                targetState = true,
                label = String.DEFAULT
            ),
            enabled = true,
            onClick = {}
        )
    }
}

@Preview(name = "FocusedDisabled")
@Preview(
    name = "FocusedDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedLeadingIconFocusedDisabledPreview() {
    Preview {
        AnimatedLeadingIcon(
            locationSearcherFocusedTransition = updateTransition(
                targetState = true,
                label = String.DEFAULT
            ),
            enabled = false,
            onClick = {}
        )
    }
}