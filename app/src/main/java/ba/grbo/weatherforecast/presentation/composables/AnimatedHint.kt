package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.R
import ba.grbo.weatherforecast.framework.mics.Constants.LocationSearcherHintExitAnimationDuration
import ba.grbo.weatherforecast.framework.mics.customFadeIn
import ba.grbo.weatherforecast.framework.mics.customFadeOut

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedHint(visible: Boolean, enabled: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = customFadeIn(DefaultDurationMillis / 2), // 150 + 150 (resetQuery animation)
        exit = customFadeOut(LocationSearcherHintExitAnimationDuration),
    ) {
        Hint(enabled = enabled)
    }
}

@Preview(name = "VisibleEnabled")
@Preview(
    name = "VisibleEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintVisibleEnabledPreview() {
    Preview {
        AnimatedHint(
            visible = true,
            enabled = true
        )
    }
}

@Preview(name = "VisibleDisabled")
@Preview(
    name = "VisibleDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintVisibleDisabledPreview() {
    Preview {
        AnimatedHint(
            visible = true,
            enabled = false
        )
    }
}

@Preview(name = "InvisibleEnabled")
@Preview(
    name = "InvisibleEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintInvisibleEnabledPreview() {
    Preview {
        AnimatedHint(
            visible = false,
            enabled = true
        )
    }
}

@Preview(name = "InvisibleDisabled")
@Preview(
    name = "InvisibleDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedHintInvisibleDisabledPreview() {
    Preview {
        AnimatedHint(
            visible = false,
            enabled = false
        )
    }
}