package ba.grbo.weatherforecast.framework.mics

import androidx.compose.ui.text.input.TextFieldValue

val String.Companion.WHITESPACE: String
    get() = " "

val String.Companion.EMPTY: String
    get() = ""


val Char.Companion.WHITESPACE: Char
    get() = ' '

fun TextFieldValue.isEmpty() = text.isEmpty()

fun TextFieldValue.isNotEmpty() = text.isNotEmpty()

fun TextFieldValue.updateText(text: String) = copy(text = text)

val TextFieldValue.Companion.Default: TextFieldValue
    get() = TextFieldValue()

