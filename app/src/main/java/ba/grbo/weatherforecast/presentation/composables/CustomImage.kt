package ba.grbo.weatherforecast.presentation.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    @StringRes description: Int,
    colorFilter: ColorFilter? = null
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = image),
        contentDescription = stringResource(id = description),
        colorFilter = colorFilter
    )
}