package ba.grbo.weatherforecast.presentation.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.weatherforecast.framework.mics.PreviewData

@Composable
fun InnerTextField(
    modifier: Modifier = Modifier,
    hintVisible: Boolean,
    enabled: Boolean,
    innerTextField: @Composable () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        innerTextField()
        AnimatedHint(visible = hintVisible, enabled = enabled)
    }
}

// Simulating the inner text with a Text
@Composable
private fun InnerText(empty: Boolean, enabled: Boolean) {
    Text(
        text = if (!empty) PreviewData.Query.Sarajevo.text else PreviewData.Query.Empty.text,
        style = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface.copy(
                if (enabled) ContentAlpha.medium else ContentAlpha.disabled
            )
        )
    )
}

@Preview(name = "NonEmptyEnabled")
@Preview(
    name = "NonEmptyEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun InnerTextFieldNonEmptyEnabledPreview() {
    Preview {
        InnerTextField(
            modifier = Modifier.fillMaxWidth(),
            hintVisible = false,
            enabled = false,
            innerTextField = { InnerText(empty = false, enabled = true) }
        )
    }
}

@Preview(name = "NonEmptyDisabled")
@Preview(
    name = "NonEmptyDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun InnerTextFieldNonEmptyDisabledPreview() {
    Preview {
        InnerTextField(
            modifier = Modifier.fillMaxWidth(),
            hintVisible = false,
            enabled = false,
            innerTextField = { InnerText(empty = false, enabled = false) },
        )
    }
}

@Preview(name = "EmptyEnabled")
@Preview(
    name = "EmptyEnabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun InnerTextFieldEmptyEnabledPreview() {
    Preview {
        InnerTextField(
            modifier = Modifier.fillMaxWidth(),
            hintVisible = true,
            enabled = true,
            innerTextField = { InnerText(empty = true, enabled = true) },
        )
    }
}

@Preview(name = "EmptyDisabled")
@Preview(
    name = "EmptyDisabled",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun InnerTextFieldEmptyDisabledPreview() {
    Preview {
        InnerTextField(
            modifier = Modifier.fillMaxWidth(),
            hintVisible = true,
            enabled = false,
            innerTextField = { InnerText(empty = true, enabled = false) },
        )
    }
}