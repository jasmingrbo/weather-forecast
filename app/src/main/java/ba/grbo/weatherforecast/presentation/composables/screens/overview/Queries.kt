package ba.grbo.weatherforecast.presentation.composables.screens.overview

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.core.interactors.overview.exceptions.FetchQueriesException
import ba.grbo.core.interactors.overview.exceptions.InsertQueryException
import ba.grbo.core.interactors.overview.exceptions.ObserveQueriesException
import ba.grbo.weatherforecast.R
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Data
import ba.grbo.weatherforecast.framework.mics.PreviewData.Queries.SarajevoAndAachen
import ba.grbo.weatherforecast.presentation.composables.Preview
import ba.grbo.weatherforecast.presentation.composables.QueryList

@Composable
fun Queries(
    value: OverviewScreenState.Value<List<String>>,
    onQueryClick: (String) -> Unit,
) {
    OverviewScreenMainContent(
        content = value,
        onContent = { queries, clickable ->
            QueryList(
                queries = queries,
                clickable = clickable,
                onQueryClick = onQueryClick
            )
        },
        emptyAnimation = R.raw.queries_empty,
        emptyMessage = R.string.queries_empty_message,
        onError = { throwable ->
            when (throwable) {
                is ObserveQueriesException,
                is FetchQueriesException -> {
                }
                is InsertQueryException -> {
                }
            }
        }
    )
}

@Preview(name = "NonEmptySarajevoAndAachen")
@Preview(
    name = "NonEmptySarajevoAndAachen",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun QueriesNonEmptySarajevoAndAachenPreview() {
    Preview {
        Queries(
            value = Data(data = SarajevoAndAachen.map { query -> query.text }),
            onQueryClick = {}
        )
    }
}