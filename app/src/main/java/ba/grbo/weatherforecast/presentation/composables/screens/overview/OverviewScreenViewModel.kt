package ba.grbo.weatherforecast.presentation.composables.screens.overview

import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.grbo.core.domain.DEFAULT
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.core.domain.dm.Location
import ba.grbo.core.domain.dm.Result
import ba.grbo.core.domain.dm.Result.Loading
import ba.grbo.core.domain.dm.Result.SourceResult.Failure
import ba.grbo.core.domain.dm.Result.SourceResult.Success
import ba.grbo.core.interactors.overview.OverviewScreenInteractors
import ba.grbo.core.interactors.overview.exceptions.FetchBasicPlacesException
import ba.grbo.core.interactors.overview.exceptions.FetchLocationsException
import ba.grbo.core.interactors.overview.exceptions.FetchQueriesException
import ba.grbo.weatherforecast.framework.data.source.remote.locationiq.LocationIQService.Companion.QUERY_LIMIT
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.ClearButtonClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.ClearQueryHistoryButtonClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.DoneImeActionClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.EnabledChanged
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.FocusChanged
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.KeyboardHidden
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.OverflowButtonClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.QueryChanged
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.AppBarEvent.UpButtonClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.BasicPlaceClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.BasicPlaceLongClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.InternetStatusChanged
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.LocationClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.QueryClicked
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.SwipedToDismiss
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenEvent.BodyEvent.SwipedToRefresh
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Data
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Error
import ba.grbo.weatherforecast.framework.logging.DefaultLogger
import ba.grbo.weatherforecast.framework.mics.EMPTY
import ba.grbo.weatherforecast.framework.mics.WHITESPACE
import ba.grbo.weatherforecast.framework.mics.copy
import ba.grbo.weatherforecast.framework.mics.filter
import ba.grbo.weatherforecast.framework.mics.invalidate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.math.roundToLong

@HiltViewModel
class OverviewScreenViewModel @Inject constructor(
    val internetStatus: StateFlow<Boolean>,
    private val interactors: OverviewScreenInteractors,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    var state by mutableStateOf(OverviewScreenState.Default)
        private set

    private val query = MutableStateFlow(TextFieldValue(text = String.DEFAULT) to true)
    private var observingQueries = false

    private var locationsQuery = String.DEFAULT

    private var fetching: Job? = null

    init {
        observePlaces()
        observeQueries()
        collectQuery()
    }

    private fun observePlaces() {
        observeValue(
            value = interactors.observeBasicPlaces(),
            onSuccess = ::onObservePlacesSuccess,
            onFailure = ::onObservePlacesFailure
        )
    }

    private fun observeQueries() {
        observeValue(
            value = interactors.observeQueries(),
            onSuccess = ::onObserveQueriesSuccess,
            onFailure = ::onObserveQueriesFailure
        )
    }

    private fun <T> observeValue(
        value: Result.SourceResult<Flow<T>>,
        onSuccess: (Flow<T>) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        when (value) {
            is Success -> onSuccess(value.data)
            is Failure -> onFailure(value.exception)
        }
    }

    private fun <T> onSuccess(
        value: Flow<T>,
        onEach: suspend (T) -> Unit,
        onException: suspend FlowCollector<T>.(Throwable) -> Unit,
    ) {
        value.onEach(onEach)
            .catch(onException)
            .launchIn(viewModelScope)
    }

    @Suppress("NAME_SHADOWING")
    private fun onObservePlacesSuccess(places: Flow<List<BasicPlace>>) {
        onSuccess(
            value = places,
            onEach = { places ->
                while (!observingQueries) delay(25) // waiting to fully observe queries
                state = state.copy(
                    appBar = state.appBar.copy(
                        enabled = places.all { place -> !place.weather.stale }
                    ),
                    body = state.body.copy(
                        loading = false,
                        places = Data(places)
                    )
                )
            },
            onException = { exception ->
                state = state.copy(
                    body = state.body.copy(
                        loading = false,
                        places = Error(FetchBasicPlacesException(exception))
                    )
                )
                DefaultLogger.e(exception)
            }
        )
    }

    private fun onObservePlacesFailure(exception: Exception) {
        state = state.copy(
            body = state.body.copy(
                loading = false,
                places = Error(exception)
            )
        )
        DefaultLogger.e(exception)
    }

    @Suppress("NAME_SHADOWING")
    private fun onObserveQueriesSuccess(queries: Flow<List<String>>) {
        onSuccess(
            value = queries,
            onEach = { queries ->
                state = state.copy(body = state.body.copy(queries = Data(queries)))
                observingQueries = true
            },
            onException = { exception ->
                state = state.copy(
                    body = state.body.copy(queries = Error(FetchQueriesException(exception)))
                )
                observingQueries = true
                DefaultLogger.e(exception)
            }
        )
    }

    private fun onObserveQueriesFailure(exception: Exception) {
        state = state.copy(body = state.body.copy(queries = Error(exception)))
        observingQueries = true
        DefaultLogger.e(exception)
    }

    fun onEvent(event: OverviewScreenEvent) = when (event) {
        is AppBarEvent -> onAppBarEvent(event)
        is BodyEvent -> onBodyEvent(event)
    }

    private fun onAppBarEvent(event: AppBarEvent): Unit = when (event) {
        is QueryChanged -> onQueryChanged(event.query)
        is FocusChanged -> onFocusChanged(event.focused)
        is EnabledChanged -> onEnabledChanged(event.enabled)
        is UpButtonClicked -> onUpButtonClicked()
        is ClearButtonClicked -> onClearButtonClicked()
        is ClearQueryHistoryButtonClicked -> onClearQueryHistoryClicked()
        is DoneImeActionClicked -> onDoneImeActionClicked()
        is KeyboardHidden -> onKeyboardHidden()
        is OverflowButtonClicked -> onOverflowButtonClicked()
    }

    private fun onBodyEvent(event: BodyEvent): Unit = when (event) {
        is InternetStatusChanged -> onInternetStatusChanged(event.internet)
        is BasicPlaceClicked -> onBasicPlaceClicked(event.placeId)
        is BasicPlaceLongClicked -> onBasicPlaceLongClicked(event.placeId)
        is SwipedToRefresh -> onSwipedToRefresh()
        is SwipedToDismiss -> onSwipedToDismiss(event.placeId)
        is QueryClicked -> onQueryClicked(event.query)
        is LocationClicked -> onLocationClicked(event.location)
    }

    private fun onInternetStatusChanged(internet: Boolean) {
        state = state.copy(body = state.body.copy(internet = internet))
        // make request for queries or location again if we have focus
        // if we have internet pull locations from internet otherwise from the db
    }

    private fun onBasicPlaceClicked(placeId: Long) {
        // disable clicks for the duration of navigation animation
    }

    private fun onBasicPlaceLongClicked(placeId: Long) {
        interactors.refreshPlace(placeId)
            .onEach { result -> DefaultLogger.e(result) }
            .catch { exception -> DefaultLogger.e(exception) }
            .launchIn(viewModelScope)
    }

    private fun onSwipedToRefresh() {
        interactors.refreshPlaces()
            .onEach { result -> DefaultLogger.e(result) }
            .catch { exception -> DefaultLogger.e(exception) }
            .launchIn(viewModelScope)
    }

    private fun onSwipedToDismiss(placeId: Long) {
        interactors.deletePlace(placeId)
            .onEach { result -> DefaultLogger.e(result) }
            .catch { exception -> DefaultLogger.e(exception) }
            .launchIn(viewModelScope)
    }

    private fun onQueryClicked(query: String) {
        disableAndReenableQueryClicks()
        animateFillQuery(query)
    }

    private fun onLocationClicked(location: Location) {
        locationsQuery = state.query
        state = state.copy(
            appBar = state.appBar.copy(unfocus = true),
            body = state.body.copy(
                locations = state.body.locations.copy(
                    locationsQuery to (state.body.locations[locationsQuery] as Data).copy(
                        clickable = false
                    )
                )
            )
        )
        animateClearQuery()
        insertQuery(state.query)
        insertPlace(location)
    }

    private fun onQueryChanged(
        query: TextFieldValue,
        filter: Boolean = true,
        fetch: Boolean = true
    ) {
        this.query.value = query.copy(
            text = if (filter) filterQuery(query.text) else query.text
        ) to fetch
    }

    private fun onFocusChanged(focused: Boolean) {
        state = if (focused) state.copy(appBar = state.appBar.copy(focused = focused))
        else state.copy(appBar = state.appBar.copy(focused = focused, unfocus = false))
    }

    private fun onEnabledChanged(enabled: Boolean) {
        state = state.copy(appBar = state.appBar.copy(enabled = enabled))
    }

    private fun onUpButtonClicked() {
        state = state.copy(appBar = state.appBar.copy(unfocus = true))
        animateClearQuery()
    }

    private fun onClearButtonClicked() {
        animateClearQuery()
    }

    private fun onClearQueryHistoryClicked() {

    }

    private fun onDoneImeActionClicked() {
        state = state.copy(appBar = state.appBar.copy(hideKeyboard = true))
    }

    private fun onKeyboardHidden() {
        state = state.copy(appBar = state.appBar.copy(hideKeyboard = false))
    }

    private fun onOverflowButtonClicked() {

    }

    private fun fetchLocations(query: String) {
        if (state.fetchLocations) {
            fetching?.cancel()
            fetching = interactors.fetchLocations(query)
                .onEach { result -> onEachLocationsResult(result, query) }
                .onCompletion { exception ->
                    if (exception is CancellationException) {
                        state = state.copy(body = state.body.copy(loading = false))
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    private fun insertQuery(query: String) {
        if (state.body.internet) interactors.insertQuery(query)
            .onEach { result -> onEachInsertQueryResult(result) }
            .launchIn(viewModelScope)
    }

    private fun insertPlace(location: Location) {
        if (state.body.internet) interactors.insertPlace(location)
            .onEach { result -> onEachInsertPlaceResult(result) }
            .launchIn(viewModelScope)
    }

    private suspend fun <T> onEachResult(
        result: Result<T>,
        onLoading: (Boolean) -> Unit,
        onSuccess: (T) -> Unit,
        onFailure: suspend (Exception) -> Unit,
    ) {
        when (result) {
            is Loading -> onLoading(result.loading)
            is Success -> onSuccess(result.data)
            is Failure -> {
                onFailure(result.exception)
                DefaultLogger.e(result.exception)
            }
        }
    }

    private suspend fun onEachLocationsResult(result: Result<List<Location>>, query: String) {
        onEachResult(
            result = result,
            onLoading = { loading ->
                state = state.copy(body = state.body.copy(loading = loading))
            },
            onSuccess = { locations ->
                state = state.copy(
                    body = state.body.copy(
                        loading = false,
                        locations = state.body.locations.copy(query to Data(locations.filter(query)))
                    )
                )
            },
            onFailure = { exception ->
                when (val cause = (exception as FetchLocationsException).cause) {
                    is HttpException -> when (cause.code()) {
                        404 -> { // Not found
                            state = state.copy(
                                body = state.body.copy(
                                    loading = false,
                                    locations = state.body.locations.copy(
                                        query to Data(listOf())
                                    )
                                )
                            )
                        }
                        429 -> { // Too many requests
                            delay(200)
                            fetchLocations(query)
                        }
                        else -> onLocationsFailure(exception = exception, query = query)
                    }
                    else -> onLocationsFailure(exception = exception, query = query)
                }
            }
        )
    }

    private fun onLocationsFailure(exception: Exception, query: String) {
        state = state.copy(
            appBar = state.appBar.copy(hideKeyboard = true),
            body = state.body.copy(
                loading = false,
                locations = state.body.locations.copy(query to Error(exception))
            )
        )
    }

    private suspend fun onEachInsertQueryResult(result: Result<Nothing>) {
        onEachResult(
            result = result,
            onLoading = { loading ->
                state = state.copy(body = state.body.copy(loading = loading))
            },
            onSuccess = {}, // Ignore
            onFailure = { exception ->
                state = state.copy(body = state.body.copy(queries = Error(exception)))
            }
        )
    }

    private suspend fun onEachInsertPlaceResult(result: Result<Boolean>) {
        onEachResult(
            result = result,
            onLoading = { loading ->
                state = if (loading) state.copy(body = state.body.copy(loading = true))
                else state.copy(
                    body = state.body.copy(
                        loading = false,
                        locations = state.body.locations.copy(
                            locationsQuery to (state.body.locations[locationsQuery] as Data).copy(
                                clickable = true
                            )
                        )
                    )
                )
            },
            onSuccess = {} /* Ignore */,
            onFailure = { exception ->
                state = state.copy(
                    body = state.body.copy(
                        loading = false,
                        places = Error(exception)
                    )
                )
            }
        )
    }

    private fun collectQuery() = viewModelScope.launch {
        query
            .onEach { (query, _) ->
                state = state.copy(
                    appBar = state.appBar.copy(query = query),
                    body = state.body.copy(locations = state.body.locations.invalidate())
                )
            }
            .collectLatest { (query, fetch) ->
                if (fetch && query.text.length > 1) {
                    delay(200) // debounce
                    fetchLocations(query.text)
                }
            }
    }

    private fun animateClearQuery(duration: Int = DefaultDurationMillis / 2) =
        viewModelScope.launch(dispatcher) {
            val query = state.query
            val delayDuration = (duration.toDouble() / (query.length - 1)).roundToLong()
            for (i in query.lastIndex downTo 0) {
                val subquery = query.substring(0, i)
                onQueryChanged(query = subquery, fetch = false)
                if (i != 0) delay(delayDuration)
            }
        }

    private fun animateFillQuery(
        query: String,
        duration: Int = DefaultDurationMillis / 2,
    ) = viewModelScope.launch(dispatcher) {
        // If one char is present, remove it
        val empty = state.query.isEmpty()
        val firstUseful = !empty && state.query.first() == query.first()
        val length = when {
            empty -> query.length - 1
            firstUseful -> query.length - 2
            else -> query.length
        }
        val delayDuration = (duration.toDouble() / length).roundToLong()

        if (!empty && !firstUseful) {
            onQueryChanged(
                query = TextFieldValue(
                    text = String.EMPTY,
                    composition = TextRange(0)
                ),
                filter = false,
                fetch = false
            )
            delay(delayDuration)
        }

        for (i in (if (firstUseful) 1 else 0)..query.lastIndex) {
            val subquery = query.substring(0..i)
            if (i != query.lastIndex) {
                onQueryChanged(query = subquery, fetch = false)
                delay(delayDuration)
            } else onQueryChanged(query = subquery, fetch = true)
        }
    }

    private fun onQueryChanged(query: String, fetch: Boolean) {
        onQueryChanged(
            query = TextFieldValue(
                text = query,
                selection = TextRange(query.length),
                composition = TextRange(0, query.length)
            ),
            filter = false,
            fetch = fetch
        )
    }

    private fun filterQuery(query: String) = when {
        query.isBlank() -> String.EMPTY
        else -> {
            val temp = (if (query.length <= QUERY_LIMIT) query else query.substring(0, QUERY_LIMIT))
                .trimStart()
                .split(Char.WHITESPACE)
                .map(::removeIfSpecial)

            temp.filterIndexed { i, s ->
                if (i == temp.lastIndex) true
                else s.isNotEmpty()
            }.joinToString(String.WHITESPACE)
        }
    }

    private fun removeIfSpecial(
        input: String,
    ) = input.toCharArray().joinToString(String.EMPTY) { char ->
        if (char.isLetter() || char.isDigit() || char == Char.WHITESPACE) char.toString()
        else String.EMPTY
    }

    private fun disableAndReenableQueryClicks() = viewModelScope.launch(dispatcher) {
        setQueriesClickable(false)
        delay(150) // fillQuery duration
        setQueriesClickable(true)
    }

    private fun setQueriesClickable(clickable: Boolean) {
        state = state.copy(
            body = state.body.copy(
                queries = (state.body.queries as Data).copy(clickable = clickable)
            )
        )
    }
}