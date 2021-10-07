package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.R

@Composable
fun CountryFlag(
    modifier: Modifier = Modifier,
    @DrawableRes flag: Int
) {
    CustomImage(
        modifier = if (MaterialTheme.colors.isLight) modifier
            .border(
                width = 0.5.dp,
                brush = SolidColor(MaterialTheme.colors.onSurface.copy(alpha = 0.12f)),
                shape = CircleShape
            )
            .padding(0.5.dp) else modifier,
        image = flag,
        description = R.string.country_flag_description
    )
}

@Preview(name = "BosniaAndHerzegovina")
@Preview(
    name = "BosniaAndHerzegovina",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun CountryFlagBosniaAndHerzegovinaPreview() {
    Preview {
        CountryFlag(flag = R.drawable.ic_bosnia_and_herzegovina)
    }
}

@Preview(name = "Germany")
@Preview(
    name = "Germany",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun CountryFlagGermanyPreview() {
    Preview {
        CountryFlag(flag = R.drawable.ic_germany)
    }
}