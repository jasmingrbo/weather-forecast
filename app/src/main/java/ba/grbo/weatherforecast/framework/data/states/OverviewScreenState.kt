package ba.grbo.weatherforecast.framework.data.states

import androidx.compose.ui.text.input.TextFieldValue
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.core.domain.dm.Location
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Uninitialized

data class OverviewScreenState(
    val appBar: OverviewAppBarState,
    val body: OverviewBodyState,
) {
    val query: String
        get() = appBar.query.text

    val fetchLocations: Boolean
        get() = body.internet && (body.locations.isEmpty() || !body.locations.containsKey(query)
                || body.locations[query] is Uninitialized)

    data class OverviewAppBarState(
        val query: TextFieldValue,
        val focused: Boolean,
        val enabled: Boolean,
        val unfocus: Boolean,
        val hideKeyboard: Boolean,
    ) {
        companion object {
            val Default = OverviewAppBarState(
                query = TextFieldValue(),
                focused = false,
                enabled = false, // Because we're loading either from the db or making network request
                unfocus = false,
                hideKeyboard = false
            )
        }
    }

    data class OverviewBodyState(
        val loading: Boolean,
        val internet: Boolean,
        val places: Value<List<BasicPlace>>,
        val queries: Value<List<String>>,
        val locations: Map<String, Value<List<Location>>>,
    ) {
        companion object {
            val Default = OverviewBodyState(
                loading = true,
                internet = true,
                places = Value.Default,
                queries = Value.Default,
                locations = mapOf()
            )
        }
    }

    sealed interface Content {
        data class Places(val value: Value<List<BasicPlace>>) : Content
        data class Queries(val value: Value<List<String>>) : Content
        data class Locations(val value: Value<List<Location>>) : Content
    }

    sealed interface Value<out T> {
        data class Data<T>(val data: T, val clickable: Boolean = true) : Value<T>
        data class Error(val throwable: Throwable) : Value<Nothing>
        object Uninitialized : Value<Nothing>

        companion object {
            val Default = Uninitialized
        }
    }

    companion object {
        val Default = OverviewScreenState(
            appBar = OverviewAppBarState.Default,
            body = OverviewBodyState.Default
        )
    }
}