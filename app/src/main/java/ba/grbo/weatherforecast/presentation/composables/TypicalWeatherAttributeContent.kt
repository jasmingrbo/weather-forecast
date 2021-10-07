package ba.grbo.weatherforecast.presentation.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TypicalWeatherAttributeContent(
    @DrawableRes imageId: Int,
    @StringRes imageDescription: Int,
    text: String,
    spacer: Dp = 5.dp,
) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = stringResource(id = imageDescription)
    )
    Spacer(modifier = Modifier.width(spacer))
    Text(
        text = text,
        style = MaterialTheme.typography.body2.copy(fontSize = 12.sp)
    )
}