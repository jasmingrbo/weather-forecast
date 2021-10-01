package ba.grbo.weatherforecast.framework.logging

import android.util.Log
import ba.grbo.weatherforecast.framework.logging.Tree.Companion.UNKNOWN_PRIORITY
import java.util.concurrent.atomic.AtomicInteger

object Counter {
    private val DEBUG = AtomicInteger(1)
    private val ERROR = AtomicInteger(1)

    fun getAndIncrement(priority: Int) = when (priority) {
        Log.DEBUG -> DEBUG.getAndIncrement()
        Log.ERROR -> ERROR.getAndIncrement()
        else -> throw IllegalArgumentException("$UNKNOWN_PRIORITY: $priority")
    }
}