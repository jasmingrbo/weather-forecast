package ba.grbo.weatherforecast.presentation.composables

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Error(
    @RawRes animation: Int,
    @StringRes message: Int,
    @StringRes actionMessage: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Animation(
            modifier = Modifier.size(300.dp),
            resource = animation
        )

        VerticalSpace(space = 48.dp)

        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = stringResource(id = message),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )

        VerticalSpace(space = 48.dp)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = actionMessage),
                textAlign = TextAlign.Center,
            )
            VerticalSpace(space = 12.dp)
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    modifier = Modifier.weight(1f),
                    onClick = { /*TODO*/ },
                    shape = CircleShape
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        text = "Report"
                    )
                }

                HorizontalSpace(space = 24.dp)


                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { /*TODO*/ },
                    shape = CircleShape
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        text = "Retry"
                    )
                }

            }
        }

        // to nullify animations' top padding
        // VerticalSpace(space = 45.dp)
    }
}