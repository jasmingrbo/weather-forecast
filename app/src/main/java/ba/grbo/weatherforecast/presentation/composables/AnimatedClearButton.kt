package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedClearButton(
    modifier: Modifier = Modifier,
    visible: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ClearButton(
            modifier = modifier,
            enabled = enabled,
            onClick = onClick
        )
    }
}

@Preview(name = "VisibleEnabled")
@Preview(
    name = "VisibleEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedClearButtonVisibleEnabledPreview() {
    Preview {
        AnimatedClearButton(
            visible = true,
            enabled = true,
            onClick = {}
        )
    }
}

@Preview(name = "VisibleDisabled")
@Preview(
    name = "VisibleDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedClearButtonVisibleDisabledPreview() {
    Preview {
        AnimatedClearButton(
            visible = true,
            enabled = false,
            onClick = {}
        )
    }
}

@Preview(name = "InvisibleEnabled")
@Preview(
    name = "InvisibleEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedClearButtonInvisibleEnabledPreview() {
    Preview {
        AnimatedClearButton(
            visible = false,
            enabled = true,
            onClick = {}
        )
    }
}

@Preview(name = "InvisibleDisabled")
@Preview(
    name = "InvisibleDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedClearButtonInvisibleDisabledPreview() {
    Preview {
        AnimatedClearButton(
            visible = false,
            enabled = false,
            onClick = {}
        )
    }
}