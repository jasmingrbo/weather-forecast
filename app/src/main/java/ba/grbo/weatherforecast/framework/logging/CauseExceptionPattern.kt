package ba.grbo.weatherforecast.framework.logging

object CauseExceptionPattern {
    val pattern = StringBuilder().apply {
        append("exception: %s\n              ")
        append("message: %s\n              ")
        append("info: %s")
    }.toString()
}