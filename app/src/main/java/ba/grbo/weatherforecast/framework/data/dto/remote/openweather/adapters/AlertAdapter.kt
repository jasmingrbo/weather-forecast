package ba.grbo.weatherforecast.framework.data.dto.remote.openweather.adapters

import ba.grbo.core.domain.DEFAULT
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.NetworkAlert
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.qualifiers.Alert
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

@Suppress("UNUSED")
object AlertAdapter {
    private const val SENDER = "sender_name"
    private const val EVENT = "event"
    private const val START = "start"
    private const val END = "end"
    private const val DESCRIPTION = "description"
    private const val TAGS = "tags"

    @FromJson
    @Alert
    fun fromJson(alerts: List<Map<String, Any>>) = NetworkAlert(
        sender = alerts[0][SENDER] as String,
        event = alerts[0][EVENT] as String,
        start = alerts[0][START] as Double,
        end = alerts[0][END] as Double,
        description = alerts[0][DESCRIPTION] as String,
        tag = (alerts[0][TAGS] as List<*>).let {
                if (it.isEmpty()) String.DEFAULT else it[0] as String
            }
    )

    @ToJson
    fun toJson(@Alert alert: NetworkAlert) = listOf(
        mapOf(
            SENDER to alert.sender,
            EVENT to alert.event,
            START to alert.start,
            END to alert.end,
            DESCRIPTION to alert.description,
            TAGS to alert.tag
        )
    )
}