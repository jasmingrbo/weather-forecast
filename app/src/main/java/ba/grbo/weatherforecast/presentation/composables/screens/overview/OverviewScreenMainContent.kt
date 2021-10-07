package ba.grbo.weatherforecast.presentation.composables.screens.overview

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Data
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Error
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Uninitialized
import ba.grbo.weatherforecast.presentation.composables.Void

@Composable
fun <A, B : List<A>> OverviewScreenMainContent(
    content: Value<B>,
    onContent: @Composable (items: B, clickable: Boolean) -> Unit,
    @RawRes emptyAnimation: Int,
    @StringRes emptyMessage: Int,
    onError: @Composable (Throwable) -> Unit
) {
    when (content) {
        is Data -> {
            if (content.data.isNotEmpty()) onContent(content.data, content.clickable)
            else Void(animation = emptyAnimation, message = emptyMessage)
        }
        is Error -> onError(content.throwable)
        is Uninitialized -> {
            // Nothing, just for sake of completion, in case we add something in future, it will
            // scream at us.
        }
    }
}

// add previews