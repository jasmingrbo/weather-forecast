package ba.grbo.weatherforecast.framework.data.dto.remote.openweather

import ba.grbo.core.domain.DEFAULT
import ba.grbo.core.domain.dm.Alert
import ba.grbo.core.domain.dm.Date
import ba.grbo.weatherforecast.framework.data.source.mapper.Mapper
import ba.grbo.weatherforecast.framework.mics.toFormattedDateAndTime

data class NetworkAlert(
    val sender: String,
    val event: String,
    val start: Double,
    val end: Double,
    val description: String,
    val tag: String
) : Mapper<Alert> {
    override fun toDomain() = Alert(
        sender = sender,
        event = event,
        date = Date(start = start.toFormattedDateAndTime(), end = end.toFormattedDateAndTime()),
        message = description,
        tag = tag
    )

    companion object {
        val DEFAULT = NetworkAlert(
            sender = String.DEFAULT,
            event = String.DEFAULT,
            start = Double.DEFAULT,
            end = Double.DEFAULT,
            description = String.DEFAULT,
            tag = String.DEFAULT
        )
    }
}