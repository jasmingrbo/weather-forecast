// package ba.grbo.weatherforecast.presentation.composables
//
// import androidx.annotation.RawRes
// import androidx.compose.foundation.layout.Arrangement
// import androidx.compose.foundation.layout.Column
// import androidx.compose.foundation.layout.Row
// import androidx.compose.foundation.layout.Spacer
// import androidx.compose.foundation.layout.fillMaxSize
// import androidx.compose.foundation.layout.fillMaxWidth
// import androidx.compose.foundation.layout.height
// import androidx.compose.foundation.layout.padding
// import androidx.compose.foundation.layout.size
// import androidx.compose.foundation.rememberScrollState
// import androidx.compose.foundation.verticalScroll
// import androidx.compose.material.Button
// import androidx.compose.material.Text
// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.getValue
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.unit.Dp
// import androidx.compose.ui.unit.dp
// import androidx.compose.ui.unit.sp
// import ba.grbo.weatherforecast.R
// import com.airbnb.lottie.compose.LottieAnimation
// import com.airbnb.lottie.compose.LottieCompositionSpec
// import com.airbnb.lottie.compose.rememberLottieComposition
//
//
// @Composable
// fun Void(
//     @RawRes resource: Int,
//     message: String
// ) {
//     Column(
//         modifier = Modifier.fillMaxSize(),
//         verticalArrangement = Arrangement.Center,
//         horizontalAlignment = Alignment.CenterHorizontally
//     ) {
//         val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resource))
//         LottieAnimation(
//             composition = composition,
//             modifier = Modifier.size(300.dp),
//             iterations = Int.MAX_VALUE
//         )
//         Spacer(modifier = Modifier.height(36.dp))
//         Text(text = message)
//     }
// }
//
// @Composable
// fun ObserveQueriesError() {
//     Error(
//         animationRes = R.raw.observe_queries_error,
//         animationSize = 300.dp,
//         message = {
//             ErrorText(text = "Unable to load query history.")
//             ErrorText(text = "You can restart the app to retry.")
//         },
//         action = {
//             ErrorAction(
//                 message = "If the problem persists clear out the history.",
//             ) {
//                 Button(onClick = { /*TODO*/ }) {
//                     Text(text = "Clear history")
//                 }
//             }
//         }
//     )
// }
//
// @Composable
// fun InsertQueryError() {
//     Error(
//         animationRes = R.raw.insertion_error,
//         message = {
//             ErrorText(text = "An unexpected error occurred.")
//             ErrorText(text = "Last query insertion failed.")
//         },
//         action = {
//             ErrorAction(message = "Retry to re-insert. Upon persistence clear history.") {
//                 Row(
//                     modifier = Modifier
//                         .padding(horizontal = 24.dp)
//                         .fillMaxWidth(),
//                     horizontalArrangement = Arrangement.SpaceBetween
//                 ) {
//                     Button(onClick = { /*TODO*/ }) {
//                         Text(text = "Retry")
//                     }
//
//                     Button(onClick = { /*TODO*/ }) {
//                         Text(text = "Clear history")
//                     }
//
//                     Button(onClick = { /*TODO*/ }) {
//                         Text(text = "Ignore")
//                     }
//                 }
//             }
//         }
//     )
// }
//
// @Composable
// fun ObserveBasicPlacesError() {
//     Error(
//         animationRes = R.raw.observe_basic_places_error,
//         message = {
//             ErrorText(text = "An unexpected error happened.")
//             ErrorText(text = "Please restart the app.")
//         },
//         action = {
//             ErrorAction(
//                 message = "If the problem persists clear out the cache."
//             ) {
//                 Button(onClick = { /*TODO*/ }) {
//                     Text(text = "Clear cache")
//                 }
//             }
//         }
//     )
// }
//
// @Composable
// fun InsertPlaceError() {
//     Error(
//         animationRes = R.raw.insertion_error,
//         message = {
//             ErrorText(text = "An unexpected error occurred.")
//             ErrorText(text = "Failed to insert chosen place.")
//         },
//         action = {
//             ErrorAction(message = "Retry to re-insert. Upon persistence clear cache.") {
//                 Row(
//                     modifier = Modifier
//                         .padding(horizontal = 24.dp)
//                         .fillMaxWidth(),
//                     horizontalArrangement = Arrangement.SpaceBetween
//                 ) {
//                     Button(onClick = { /*TODO*/ }) {
//                         Text(text = "Retry")
//                     }
//
//                     Button(onClick = { /*TODO*/ }) {
//                         Text(text = "Clear cache")
//                     }
//
//                     Button(onClick = { /*TODO*/ }) {
//                         Text(text = "Ignore")
//                     }
//                 }
//             }
//         }
//     )
// }
//
// @Composable
// fun Error(
//     @RawRes animationRes: Int,
//     animationSize: Dp = 250.dp,
//     message: @Composable () -> Unit,
//     action: @Composable () -> Unit
// ) {
//     Column(
//         modifier = Modifier
//             .padding(top = 12.dp, bottom = 12.dp)
//             .fillMaxSize()
//             .verticalScroll(rememberScrollState()),
//         verticalArrangement = Arrangement.Center,
//         horizontalAlignment = Alignment.CenterHorizontally
//     ) {
//         ErrorAnimation(resource = animationRes, size = animationSize)
//         ErrorMessage { message() }
//         action()
//     }
// }
//
// @Composable
// fun ErrorAnimation(
//     @RawRes resource: Int,
//     size: Dp
// ) {
//     val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resource))
//     LottieAnimation(
//         composition = composition,
//         modifier = Modifier.size(size),
//         iterations = Int.MAX_VALUE
//     )
// }
//
// @Composable
// fun ErrorMessage(
//     content: @Composable () -> Unit
// ) {
//     Column(
//         modifier = Modifier
//             .fillMaxWidth()
//             .padding(
//                 start = 36.dp,
//                 end = 36.dp,
//                 top = 48.dp,
//                 bottom = 48.dp
//             ),
//         horizontalAlignment = Alignment.CenterHorizontally
//     ) {
//         content()
//         // ErrorText(text = "An unexpected error happened.")
//         // ErrorText(text = "Please restart the app.")
//     }
// }
//
// @Composable
// fun ErrorText(text: String) {
//     Text(
//         text = text,
//         fontSize = 20.sp
//     )
// }
//
// @Composable
// fun ErrorAction(
//     message: String,
//     content: @Composable () -> Unit
// ) {
//     Text(text = message)
//     Spacer(modifier = Modifier.height(24.dp))
//     content()
// }