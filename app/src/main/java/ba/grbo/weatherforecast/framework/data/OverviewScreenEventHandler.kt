package ba.grbo.weatherforecast.framework.data

import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.EnabledChanged
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.FocusChanged
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.OverflowButtonClicked
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.QueryChanged
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.ResetButtonClicked
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.AppBarEvent.UpButtonClicked
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.KeyboardEvent
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.KeyboardEvent.DoneImeActionClicked
import ba.grbo.weatherforecast.framework.data.OverviewScreenEvent.KeyboardEvent.KeyboardHidden
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToLong

@OptIn(ExperimentalCoroutinesApi::class)
class OverviewScreenEventHandler @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
) {
    private var resetQueryJob: Job? = null

    operator fun invoke(
        event: OverviewScreenEvent,
        state: OverviewScreenState,
    ) = channelFlow {
        when (event) {
            is AppBarEvent -> handleAppBarEvent(event, state)
            is KeyboardEvent -> handleKeyboardEvent(event, state)
        }
    }.flowOn(dispatcher)

    private suspend fun ProducerScope<OverviewScreenState>.handleAppBarEvent(
        event: AppBarEvent,
        state: OverviewScreenState,
    ) {
        when (event) {
            is QueryChanged -> onQueryChanged(event.query, state)
            is FocusChanged -> onFocusChanged(event.focused, state)
            is EnabledChanged -> onEnabledChanged(event.enabled, state)
            is UpButtonClicked -> onUpButtonClicked(state)
            is ResetButtonClicked -> onResetButtonClicked(state)
            is OverflowButtonClicked -> {
            }
        }
    }

    private suspend fun ProducerScope<OverviewScreenState>.handleKeyboardEvent(
        event: KeyboardEvent,
        state: OverviewScreenState,
    ) {
        when (event) {
            is DoneImeActionClicked -> onDoneImeActionClicked(state)
            is KeyboardHidden -> onKeyboardHidden(state)
        }
    }

    private suspend fun ProducerScope<OverviewScreenState>.onQueryChanged(
        query: String,
        state: OverviewScreenState,
    ) {
        resetQueryJob?.cancel()
        send(state.updateQuery(query))
    }

    private suspend fun ProducerScope<OverviewScreenState>.onFocusChanged(
        focused: Boolean,
        state: OverviewScreenState,
    ) = if (focused) onFocused(state) else onUnfocused(state)


    private suspend fun ProducerScope<OverviewScreenState>.onFocused(
        state: OverviewScreenState,
    ) {
        resetQueryJob?.cancel()
        send(state.updateFocusedToTrue())
    }

    private suspend fun ProducerScope<OverviewScreenState>.onUnfocused(
        state: OverviewScreenState,
    ) = coroutineScope {
        val unfocused = state.updateFocusedAndUnfocusToFalse()
        send(unfocused)
        resetQueryJob = launch { resetQuery(unfocused) }
    }

    private suspend fun ProducerScope<OverviewScreenState>.onEnabledChanged(
        enabled: Boolean,
        state: OverviewScreenState,
    ) {
        send(state.updateEnabled(enabled))
    }

    private suspend fun ProducerScope<OverviewScreenState>.onUpButtonClicked(
        state: OverviewScreenState,
    ) {
        send(state.updateUnfocusToTrue()) // clears focus and triggers onFocusChanged
    }

    private suspend fun ProducerScope<OverviewScreenState>.onResetButtonClicked(
        state: OverviewScreenState,
    ) = coroutineScope {
        resetQueryJob = launch { resetQuery(state) }
    }

    private suspend fun ProducerScope<OverviewScreenState>.onDoneImeActionClicked(
        state: OverviewScreenState,
    ) {
        send(state.updateHideKeyboard(true))
    }

    private suspend fun ProducerScope<OverviewScreenState>.onKeyboardHidden(
        state: OverviewScreenState,
    ) {
        send(state.updateHideKeyboard(false))
    }

    private suspend fun ProducerScope<OverviewScreenState>.resetQuery(
        state: OverviewScreenState,
        duration: Double = 150.0,
    ) {
        if (state.appBar.query.isEmpty()) return

        val delayDuration = (duration / state.appBar.query.length).roundToLong()

        suspend fun updateQuery(query: String) {
            if (query.isEmpty()) send(state.updateQuery(query))
            else {
                val temp = query.substring(0 until query.lastIndex)
                send(state.updateQuery(temp))
                delay(delayDuration)
                // Don't have to check for coroutineContext.isActive, because we are calling
                // delay, which is a library function, and all library functions are cancellable
                // by default, that is they check whether or not a job is in active state
                updateQuery(temp)
            }
        }

        updateQuery(state.appBar.query)
    }
}