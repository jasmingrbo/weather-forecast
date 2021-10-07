package ba.grbo.weatherforecast.framework.mics

import android.annotation.SuppressLint
import android.content.res.Resources
import android.text.format.DateFormat
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import ba.grbo.core.domain.DEFAULT
import ba.grbo.core.domain.dm.BasicPlace
import ba.grbo.core.domain.dm.Location
import ba.grbo.core.domain.dm.Wind
import ba.grbo.weatherforecast.R
import ba.grbo.weatherforecast.framework.data.dto.local.CachedQuery
import ba.grbo.weatherforecast.framework.data.source.mapper.Mapper
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Error
import ba.grbo.weatherforecast.framework.data.states.OverviewScreenState.Value.Uninitialized
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.roundToLong

val String.Companion.WHITESPACE: String
    get() = " "

val String.Companion.EMPTY: String
    get() = String.DEFAULT

val Char.Companion.WHITESPACE: Char
    get() = ' '

fun String.toData() = CachedQuery(value = this)

fun Int.formatAsVisibility() = "${this / 1000.0}km"

fun Int.formatAsHumidity() = "$this%"

fun Double.formatAsUvi() = "${this.roundTo(1)}"

fun Double.roundTo(numFractionDigits: Int): Double {
    val factor = 10.0.pow(numFractionDigits.toDouble())
    return (this * factor).roundToInt() / factor
}

fun Wind.format() = "${speed.roundTo(1)}m/s, $direction"

fun <O> Iterable<Mapper<O>>.toDomain() = map(Mapper<O>::toDomain)

fun <O> Flow<Iterable<Mapper<O>>>.toDomain() = map { iterable -> iterable.toDomain() }

fun Double.toFormattedDateAndTime(
    locale: Locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0],
): String = SimpleDateFormat(
    DateFormat.getBestDateTimePattern(locale, "MMMM d HH:mm"),
    locale
).format(Date(this.roundToLong() * 1000)) /// 1000 to convert it to milliseconds

fun Double.toFormattedDate(
    locale: Locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0],
): String = SimpleDateFormat(
    DateFormat.getBestDateTimePattern(locale, "EEEE MMMM d"),
    locale
).format(Date(this.roundToLong() * 1000))

fun Double.toFormattedTime(
    style: Int = SimpleDateFormat.SHORT,
    locale: Locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0],
): String = SimpleDateFormat.getTimeInstance(
    style,
    locale
).format(Date(this.roundToLong() * 1000))

@OptIn(ExperimentalStdlibApi::class)
fun <K, V> Map<K, V>.copy(vararg entries: Pair<K, V>): Map<K, V> = buildMap {
    putAll(this@copy)
    putAll(entries)
}

@OptIn(ExperimentalStdlibApi::class)
fun <T> Map<String, Value<T>>.invalidate(): Map<String, Value<T>>  {
    val errors = filter { (_, value) -> value is Error }
        .map { (key, _) -> key to Uninitialized }
        .toTypedArray()
    return copy(*errors)
}

fun <K, V> Map<K, V>.retrieveOrDefault(key: K, default: V) =
    if (containsKey(key)) get(key)!! else default

fun List<Location>.filter(query: String) =
    filter { location -> query.normalize() in location.name.normalize() }
        .groupBy { location -> location.coordinates }
        .map { (_, location) -> location.first() }

private fun String.normalize() = Normalizer
    .normalize(this.lowercase(), Normalizer.Form.NFD)
    .replace("[^\\p{ASCII}]".toRegex(), String.EMPTY)


val BasicPlace.airQualityIconResource: Int
    get() = when (airQualityIndex) {
        1 -> R.drawable.ic_aq_good
        2 -> R.drawable.ic_aq_fair
        3 -> R.drawable.ic_aq_moderate
        4 -> R.drawable.ic_aq_poor
        5 -> R.drawable.ic_aq_very_poor
        else -> throw IllegalArgumentException("Unknown airQualityIndex: $airQualityIndex")
    }

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.roundedShimmeringPlaceholder(
    visible: Boolean,
    size: Dp = 6.dp,
): Modifier = composed {
    clip(RoundedCornerShape(size)).placeholder(
        visible = visible,
        highlight = PlaceholderHighlight.shimmer()
    )
}