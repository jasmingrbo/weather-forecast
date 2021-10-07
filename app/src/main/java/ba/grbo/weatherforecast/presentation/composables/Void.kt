package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.weatherforecast.R

@Composable
fun Void(
    @RawRes animation: Int,
    @StringRes message: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Animation(
            modifier = Modifier.size(300.dp),
            resource = animation
        )

        VerticalSpace(space = 12.dp)

        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = stringResource(id = message),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )

        // to nullify the animations' top padding, so that everything looks centered
        VerticalSpace(space = 33.dp)
    }
}

@Preview(name = "Places")
@Preview(
    name = "Places",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun VoidPlacesPreview() {
    Preview {
        Void(
            animation = R.raw.places_empty,
            message = R.string.places_empty_message
        )
    }
}