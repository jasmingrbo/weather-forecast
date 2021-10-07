package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.weatherforecast.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InternetStatusBanner(internet: Boolean) {
    val transition = updateTransition(
        targetState = internet,
        label = "InternetStatusBanner-Transition"
    )
    val surfaceColor by transition.animateColor(label = "surfaceColor") { targetHasInternet ->
        if (targetHasInternet) MaterialTheme.colors.secondary
        else MaterialTheme.colors.error
    }
    Surface(
        shape = RectangleShape,
        color = surfaceColor,
    ) {
        transition.AnimatedContent { internet ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp, bottom = 1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(14.dp),
                    painter = painterResource(
                        if (internet) R.drawable.ic_wifi_on else R.drawable.ic_wifi_off
                    ),
                    contentDescription = stringResource(
                        if (internet) R.string.wifi_on_description
                        else R.string.wifi_off_description
                    ),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(
                        if (internet) R.string.wifi_on_text else R.string.wifi_off_text
                    ),
                    style = MaterialTheme.typography.body2.copy(fontSize = 13.sp)
                )
            }
        }
    }
}

@Preview(name = "WithInternet")
@Preview(
    name = "WithInternet",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun InternetStatusBannerWithInternetPreview() {
    Preview {
        InternetStatusBanner(internet = true)
    }
}

@Preview(name = "WithoutInternet")
@Preview(
    name = "WithoutInternet",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun InternetStatusBannerWithoutInternetPreview() {
    Preview {
        InternetStatusBanner(internet = false)
    }
}