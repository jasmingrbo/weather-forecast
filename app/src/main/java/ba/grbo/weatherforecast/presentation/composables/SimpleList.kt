package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex

@Composable
fun <T> SimpleList(
    modifier: Modifier = Modifier,
    items: List<T>,
    text: (T) -> String,
    key: (index: Int, item: T) -> Any,
    clickable: Boolean,
    onItemCardClick: (T) -> Unit,
    icon: @Composable (T) -> Unit
) {
    val state = rememberLazyListState()
    val firstItemCompletelyVisible = remember(key1 = state) {
        state.firstVisibleItemIndex == 0 && state.firstVisibleItemScrollOffset == 0
    }
    CustomLazyColumn(
        modifier = modifier
            .zIndex(1f)
            .fillMaxHeight(),
        items = items,
        key = key,
        state = state
    ) { index, item ->
        SimpleListItem(
            item = item,
            clickable = clickable,
            text = text(item),
            divider = index != items.lastIndex || firstItemCompletelyVisible,
            onClick = onItemCardClick,
            icon = { icon(item) }
        )
    }
}