package ba.grbo.weatherforecast.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDismissPlaceBackground(
    modifier: Modifier = Modifier,
    state: DismissState,
    last: Boolean,
    onSwipedToDismiss: () -> Unit
) {
    val flag = with(LocalDensity.current) {
        abs(state.offset.value.toDp()) < 20.dp
    }

    if (state.currentValue != DismissValue.Default) LaunchedEffect(key1 = state.currentValue) {
        onSwipedToDismiss()
    }

    if (!state.isDismissed(DismissDirection.StartToEnd) && state.dismissDirection == DismissDirection.StartToEnd) {
        Row(
            modifier = modifier
                .padding(
                    start = 12.dp,
                    end = if (flag) 12.dp else 0.dp,
                    top = 12.dp,
                    bottom = if (last) 12.dp else 0.dp
                )
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colors.error,
                    shape = RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = if (flag) 8.dp else 0.dp,
                        bottomStart = 8.dp,
                        bottomEnd = if (flag) 8.dp else 0.dp
                    )
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .padding(start = 18.dp)
                    .size(36.dp),
                tint = MaterialTheme.colors.onError
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Delete",
                color = MaterialTheme.colors.onError,
                fontSize = 20.sp
            )
        }
    } else if (!state.isDismissed(DismissDirection.EndToStart) && state.dismissDirection == DismissDirection.EndToStart) {
        Row(
            modifier = modifier
                .padding(
                    start = if (flag) 12.dp else 0.dp,
                    end = 12.dp,
                    top = 12.dp,
                    bottom = if (last) 12.dp else 0.dp
                )
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colors.error,
                    shape = RoundedCornerShape(
                        topStart = if (flag) 8.dp else 0.dp,
                        topEnd = 8.dp,
                        bottomStart = if (flag) 8.dp else 0.dp,
                        bottomEnd = 8.dp
                    )
                ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Delete",
                color = MaterialTheme.colors.onError,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .padding(end = 18.dp)
                    .size(36.dp),
                tint = MaterialTheme.colors.onError
            )
        }
    }
}

fun abs(dp: Dp) = if (dp < 0.dp) -dp else dp