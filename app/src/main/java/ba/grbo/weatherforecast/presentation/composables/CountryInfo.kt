package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.R

@Composable
fun RowScope.CountryInfo(
    @DrawableRes flag: Int,
    placeName: String
) {
    CountryFlag(
        modifier = Modifier.size(24.dp),
        flag = flag
    )

    HorizontalSpace(space = 12.dp)

    // Place name
    Text(
        text = placeName,
        modifier = Modifier.weight(1f),
        style = MaterialTheme.typography.h6
    )

    HorizontalSpace(space = 12.dp)
}

@Preview(name = "Sarajevo")
@Preview(
    name = "Sarajevo",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun CountryInfoSarajevoPreview() {
    Preview {
        Row {
            CountryInfo(
                flag = R.drawable.ic_bosnia_and_herzegovina,
                placeName = "Sarajevo"
            )
        }
    }
}

@Preview(name = "Berlin")
@Preview(
    name = "Berlin",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun CountryInfoBerlinPreview() {
    Preview {
        Row {
            CountryInfo(
                flag = R.drawable.ic_germany,
                placeName = "Berlin"
            )
        }
    }
}