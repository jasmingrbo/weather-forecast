package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.R
import ba.grbo.weatherforecast.framework.mics.PreviewData.Queries.SarajevoAndAachen as SarajevoAndAachen1

@Composable
fun QueryList(
    queries: List<String>,
    clickable: Boolean,
    onQueryClick: (String) -> Unit,
) {
    SimpleList(
        items = queries,
        text = { query -> query },
        key = { _, query -> query },
        clickable = clickable,
        onItemCardClick = onQueryClick,
        icon = {
            Icon(
                modifier = Modifier.padding(start = 4.dp, end = 20.dp),
                painter = painterResource(id = R.drawable.ic_history),
                contentDescription = stringResource(id = R.string.history_icon_description)
            )
        }
    )
}

@Preview(name = "NonEmptySarajevoAndAachen")
@Preview(
    name = "NonEmptySarajevoAndAachen",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun QueryListNonEmptySarajevoAndAachenPreview() {
    Preview {
        QueryList(
            queries = SarajevoAndAachen1.map { query -> query.text },
            clickable = true,
            onQueryClick = {}
        )
    }
}