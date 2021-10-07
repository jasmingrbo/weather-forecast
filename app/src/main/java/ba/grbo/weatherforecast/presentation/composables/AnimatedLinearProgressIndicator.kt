package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.framework.mics.customFadeIn
import ba.grbo.weatherforecast.framework.mics.customFadeOut

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedLinearProgressIndicator(loading: Boolean) {
    AnimatedVisibility(
        visible = loading,
        enter = customFadeIn(),
        exit = customFadeOut()
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.5.dp)
        )
    }
}

@Preview(name = "Loading")
@Preview(
    name = "Loading",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedLinearProgressIndicatorLoadingPreview() {
    Preview {
        AnimatedLinearProgressIndicator(loading = true)
    }
}

@Preview(name = "NotLoading")
@Preview(
    name = "NotLoading",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AnimatedLinearProgressIndicatorNotLoadingPreview() {
    Preview {
        AnimatedLinearProgressIndicator(loading = false)
    }
}