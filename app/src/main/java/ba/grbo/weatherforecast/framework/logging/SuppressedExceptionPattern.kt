package ba.grbo.weatherforecast.framework.logging

import ba.grbo.weatherforecast.BuildConfig

object SuppressedExceptionPattern {
    private val BASE = StringBuilder().apply {
        append("%d. ")
        append("exception: %s\n                 ")
        append("message: %s\n                 ")
        append("info: %s")
    }

    private val START = "$BASE\n"
    private val MIDDLE = "              $BASE\n"
    private val END = "              $BASE"
    private const val UNSPACED = "%d. exception: %s message: %s info: %s"

    fun get(
        index: Int,
        lastIndex: Int
    ) = if (BuildConfig.DEBUG) when (index) {
        0 -> START
        lastIndex -> END
        else -> MIDDLE
    } else UNSPACED
}