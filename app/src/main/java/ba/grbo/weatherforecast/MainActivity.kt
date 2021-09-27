package ba.grbo.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.createChildTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ba.grbo.weatherforecast.ui.theme.WeatherForecastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { WeatherForecastApp() }
    }
}

data class WeatherForecastAppBarState(
    val isFocused: Boolean,
    val locationSearcherState: LocationSearcherState,
)

data class LocationSearcherState(val query: String, val isEnabled: Boolean)

// When we introduce ViewModel this is going to be an object
data class WeatherForecastAppBarCallbacks(
    val locationSearcherCallbacks: LocationSearcherCallbacks,
    val onOverflowClicked: () -> Unit,
)

// When we introduce ViewModel this is going to be an object
data class LocationSearcherCallbacks(
    val onFocusChanged: (Boolean) -> Unit,
    val clearFocus: () -> Unit,
    val onQueryChange: (String) -> Unit,
    val onEnabledChange: (Boolean) -> Unit,
    val onCloseClicked: () -> Unit
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherForecastApp() {
    WeatherForecastTheme {
        Surface {
            val (text, setText) = remember { mutableStateOf("") }
            val (isEnabled, setIsEnabled) = remember { mutableStateOf(true) }
            val (isFocused, setIsFocused) = remember { mutableStateOf(false) }
            val focusManager = LocalFocusManager.current
            val clearFocus = { focusManager.clearFocus(true) }

            WeatherForecastAppBar(
                state = WeatherForecastAppBarState(
                    isFocused,
                    LocationSearcherState(text, isEnabled)
                ),
                callbacks = WeatherForecastAppBarCallbacks(
                    locationSearcherCallbacks = LocationSearcherCallbacks(
                        onFocusChanged = setIsFocused,
                        clearFocus = clearFocus,
                        onQueryChange = setText,
                        onEnabledChange = setIsEnabled,
                        onCloseClicked = { setText("") },
                    ),
                    onOverflowClicked = {}
                )
            )

            // CompareTwoTextFields(
            //     text = text,
            //     enabled = enabled,
            //     setText = setText,
            //     setEnabled = setEnabled
            // )
        }
    }
}

@OptIn(ExperimentalTransitionApi::class)
@Composable
fun WeatherForecastAppBar(
    state: WeatherForecastAppBarState,
    callbacks: WeatherForecastAppBarCallbacks
) {
    Column {
        Row(
            modifier = Modifier.pointerInput(state.isFocused) {
                detectTapGestures(onPress = {
                    if (state.isFocused) callbacks.locationSearcherCallbacks.clearFocus()
                })
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            val transition = updateTransition(
                targetState = state.isFocused,
                label = "WeatherForecastAppBarTransition"
            )
            LocationSearcher(
                modifier = Modifier.weight(1f),
                state = state.locationSearcherState,
                isFocusedTransition = transition.createChildTransition { isFocused -> isFocused },
                callbacks = callbacks.locationSearcherCallbacks
            )
            AnimatedOverflowButton(
                isLocationSearcherFocusedTransition = transition.createChildTransition { isFocused ->
                    isFocused
                },
                onClicked = callbacks.onOverflowClicked
            )
        }
        Divider()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LocationSearcher(
    modifier: Modifier = Modifier,
    state: LocationSearcherState,
    isFocusedTransition: Transition<Boolean>,
    callbacks: LocationSearcherCallbacks
) {
    val endPadding by createHorizontalPadding(
        isFocusedTransition = isFocusedTransition,
        focusedPadding = 12.dp,
        unfocusedPadding = 2.dp
    )

    BasicTextField(
        modifier = modifier
            .padding(start = 12.dp, end = endPadding, top = 8.dp, bottom = 8.dp)
            .onFocusChanged { callbacks.onFocusChanged(it.isFocused) },
        value = state.query,
        onValueChange = callbacks.onQueryChange,
        textStyle = LocalTextStyle.current.copy(
            color = LocalContentColor.current.copy(
                if (state.isEnabled) LocalContentAlpha.current
                else ContentAlpha.disabled
            )
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { callbacks.clearFocus() }),
        singleLine = true,
        enabled = state.isEnabled,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        decorationBox = { innerTextField ->
            LocationSearcherDecorationBox(
                state = state,
                innerTextField = innerTextField,
                callbacks = callbacks
            )
        }
    )
}

@Composable
private fun createHorizontalPadding(
    isFocusedTransition: Transition<Boolean>,
    focusedPadding: Dp,
    unfocusedPadding: Dp
) = isFocusedTransition.animateDp(
    transitionSpec = {
        tween(
            durationMillis = if (targetState) 200 else DefaultDurationMillis,
            easing = if (targetState) FastOutLinearInEasing else LinearOutSlowInEasing
        )
    },
    label = "startPadding"
) { isFocused -> if (isFocused) focusedPadding else unfocusedPadding }

@Composable
private fun LocationSearcherDecorationBox(
    state: LocationSearcherState,
    innerTextField: @Composable () -> Unit,
    callbacks: LocationSearcherCallbacks
) {
    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colors.onSurface.copy(
                    alpha = TextFieldDefaults.BackgroundOpacity
                ),
                shape = CircleShape
            )
            .heightIn(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MagnifierIcon(state)
        TextField(innerTextField, state)
        AnimatedResetButton(state, callbacks)
    }
}

@Composable
private fun MagnifierIcon(state: LocationSearcherState) {
    Icon(
        modifier = Modifier.padding(start = 12.dp, end = 16.dp),
        imageVector = Icons.Default.Search,
        contentDescription = "Search",
        tint = MaterialTheme.colors.onSurface.copy(
            alpha = if (state.isEnabled) TextFieldDefaults.IconOpacity
            else ContentAlpha.disabled
        )
    )
}

@Composable
private fun RowScope.TextField(
    innerTextField: @Composable () -> Unit,
    state: LocationSearcherState
) {
    Box(
        modifier = Modifier.Companion.weight(1f),
        contentAlignment = Alignment.CenterStart
    ) {
        innerTextField()
        if (state.query.isEmpty()) Text(
            text = "Search for a place",
            style = LocalTextStyle.current.copy(
                color = MaterialTheme.colors.onSurface.copy(
                    if (state.isEnabled) ContentAlpha.medium else ContentAlpha.disabled
                )
            )
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun RowScope.AnimatedResetButton(
    state: LocationSearcherState,
    callbacks: LocationSearcherCallbacks
) {
    AnimatedVisibility(
        visible = state.query.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ResetButton(callbacks, state)
    }
}

@Composable
private fun ResetButton(
    callbacks: LocationSearcherCallbacks,
    state: LocationSearcherState
) {
    IconButton(
        modifier = Modifier.padding(start = 4.dp, end = 0.dp),
        onClick = callbacks.onCloseClicked,
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = MaterialTheme.colors.onSurface.copy(
                alpha = if (state.isEnabled) TextFieldDefaults.IconOpacity
                else ContentAlpha.disabled
            )
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedOverflowButton(
    isLocationSearcherFocusedTransition: Transition<Boolean>,
    onClicked: () -> Unit
) {
    isLocationSearcherFocusedTransition.AnimatedVisibility(
        visible = { isFocused -> !isFocused },
        enter = fadeIn(animationSpec = tween(easing = LinearOutSlowInEasing)) + expandHorizontally(
            expandFrom = Alignment.Start,
            animationSpec = tween(easing = LinearOutSlowInEasing)
        ),
        exit = fadeOut(animationSpec = tween(durationMillis = 200, easing = FastOutLinearInEasing))
                + shrinkHorizontally(
            shrinkTowards = Alignment.Start,
            animationSpec = tween(durationMillis = 200, easing = FastOutLinearInEasing)
        )
    ) {
        OverflowButton(onClicked)
    }
}

@Composable
private fun OverflowButton(onClicked: () -> Unit) {
    IconButton(
        modifier = Modifier.padding(end = 2.dp),
        onClick = onClicked
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "MoreVert"
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CompareTwoTextFields(
    text: String,
    enabled: Boolean,
    setText: (String) -> Unit,
    setEnabled: (Boolean) -> Unit
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end = 4.dp, top = 8.dp, bottom = 8.dp),
                shape = CircleShape,
                enabled = enabled,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                singleLine = true,
                placeholder = { Text(text = "Sea") },
                trailingIcon = {
                    AnimatedVisibility(
                        visible = text.isNotBlank(),
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        IconButton(
                            modifier = Modifier.padding(end = 4.dp),
                            onClick = { setText("") },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close"
                            )
                        }
                    }
                },
                value = text,
                onValueChange = setText
            )
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end = 4.dp, top = 8.dp, bottom = 8.dp),
                value = text,
                onValueChange = setText,
                textStyle = LocalTextStyle.current.copy(
                    color = LocalContentColor.current.copy(
                        if (enabled) LocalContentAlpha.current
                        else ContentAlpha.disabled
                    )
                ),
                singleLine = true,
                enabled = enabled,
                cursorBrush = SolidColor(MaterialTheme.colors.primary),
                decorationBox = {
                    Row(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.onSurface.copy(
                                    alpha = TextFieldDefaults.BackgroundOpacity
                                ),
                                shape = CircleShape
                            )
                            .heightIn(56.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.padding(start = 12.dp, end = 16.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colors.onSurface.copy(
                                alpha = if (enabled) TextFieldDefaults.IconOpacity
                                else ContentAlpha.disabled
                            )
                        )
                        Box(modifier = Modifier.weight(1f)) {
                            it()
                            if (text.isEmpty()) Text(
                                text = "Sea",
                                color = MaterialTheme.colors.onSurface.copy(
                                    if (enabled) ContentAlpha.medium else ContentAlpha.disabled
                                )
                            )
                        }
                        AnimatedVisibility(
                            visible = text.isNotEmpty(),
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            IconButton(
                                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                                onClick = { setText("") },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = MaterialTheme.colors.onSurface.copy(
                                        alpha = if (enabled) TextFieldDefaults.IconOpacity
                                        else ContentAlpha.disabled
                                    )
                                )
                            }
                        }
                    }
                }
            )
            IconButton(
                modifier = Modifier.padding(end = 4.dp),
                onClick = { }
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "..")
            }
        }
        Divider()
        Button(onClick = { setEnabled(!enabled) }) {
            Text(text = "Enabled = $enabled")
        }
    }
}