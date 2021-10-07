package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDismissPlace(
    modifier: Modifier = Modifier,
    last: Boolean,
    onSwipedToDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    val state = rememberDismissState()
    SwipeToDismiss(
        state = state,
        background = {
            SwipeToDismissPlaceBackground(
                modifier = modifier,
                state = state,
                last = last,
                onSwipedToDismiss = onSwipedToDismiss
            )
        }
    ) {
        content()
    }
}
