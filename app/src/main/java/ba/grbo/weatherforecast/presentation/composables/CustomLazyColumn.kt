package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> CustomLazyColumn(
    modifier: Modifier = Modifier,
    items: List<T>,
    key: (index: Int, item: T) -> Any,
    state: LazyListState,
    itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = state
    ) {
        itemsIndexed(
            items = items,
            key = key,
            itemContent = itemContent
        )
    }
}