package ba.grbo.weatherforecast.presentation.composables

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun Animation(
    modifier: Modifier = Modifier,
    @RawRes resource: Int
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resource))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = Int.MAX_VALUE
    )
}