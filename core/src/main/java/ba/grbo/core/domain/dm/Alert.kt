package ba.grbo.core.domain.dm

import ba.grbo.core.domain.DEFAULT

data class Alert(
    val sender: String,
    val event: String,
    val date: Date,
    val message: String,
    val tag: String
) {
    companion object {
        val DEFAULT = Alert(
            sender = String.DEFAULT,
            event = String.DEFAULT,
            date = Date.DEFAULT,
            message = String.DEFAULT,
            tag = String.DEFAULT
        )
    }
}