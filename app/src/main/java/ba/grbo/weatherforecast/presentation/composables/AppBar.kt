package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ba.grbo.weatherforecast.framework.mics.PreviewData
import ba.grbo.weatherforecast.presentation.composables.screens.overview.OverviewScreenAppBarContent

@Composable
fun AppBar(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.zIndex(2f),
        shape = RectangleShape,
        elevation = 3.dp
    ) {
        Column {
            content()
            Divider(thickness = 0.5.dp)
        }
    }
}

@Preview(name = "OverviewScreenNonEmptyUnfocusedEnabled")
@Preview(
    name = "OverviewScreenNonEmptyUnfocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AppBarOverviewScreenNonEmptyUnfocusedEnabledPreview() {
    Preview {
        AppBar {
            OverviewScreenAppBarContent(
                query = PreviewData.Query.Sarajevo,
                focused = false,
                enabled = true,
                unfocus = false,
                hideKeyboard = false,
                onEvent = {}
            )
        }
    }
}

@Preview(name = "OverviewScreenEmptyUnfocusedEnabled")
@Preview(
    name = "OverviewScreenEmptyUnfocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AppBarOverviewScreenEmptyUnfocusedEnabledPreview() {
    Preview {
        AppBar {
            OverviewScreenAppBarContent(
                query = PreviewData.Query.Empty,
                focused = false,
                enabled = true,
                unfocus = false,
                hideKeyboard = false,
                onEvent = {}
            )
        }
    }
}

@Preview(name = "OverviewScreenNonEmptyFocusedEnabled")
@Preview(
    name = "OverviewScreenNonEmptyFocusedEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun AppBarOverviewScreenNonEmptyFocusedEnabledPreview() {
    Preview {
        AppBar {
            OverviewScreenAppBarContent(
                query = PreviewData.Query.Sarajevo,
                focused = true,
                enabled = true,
                unfocus = false,
                hideKeyboard = false,
                onEvent = {}
            )
        }
    }
}