package ba.grbo.weatherforecast.framework.logging

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics

val StackTraceElement.info: String
    get() = String.format(
        "%s.%s (%s:%s)",
        className.substring(className.lastIndexOf(".") + 1),
        methodName,
        fileName,
        lineNumber
    )

val StackTraceElement.next: StackTraceElement
    get() {
        val stackTrace = Throwable().stackTrace
        val index = stackTrace.indexOf(this)
        return stackTrace[index + 1]
    }

val Throwable?.simpleClassNameOrNone: String
    get() = if (this != null) this::class.simpleName ?: Tree.EMPTY_MESSAGE else Tree.EMPTY_MESSAGE

val Throwable?.messageOrNone: String
    get() = this?.message ?: Tree.EMPTY_MESSAGE

val Throwable?.suppressedInfoOrNone: String
    get() {
        return if (this != null && this.suppressed.isNotEmpty()) {
            val sB = StringBuilder()
            suppressed.forEachIndexed { i, t ->
                sB.append(t.formatAsSuppressed(i, suppressed.lastIndex))
            }
            sB.toString()
        } else Tree.EMPTY_MESSAGE
    }

private fun Throwable.formatAsSuppressed(index: Int, lastIndex: Int) = String.format(
    SuppressedExceptionPattern.get(index, lastIndex),
    index + 1,
    simpleClassNameOrNone,
    messageOrNone,
    stackTrace[0].info
)

val Int.tag: String
    get() = when (this) {
        Log.DEBUG -> Tree.DEBUG_LOGGING_TAG
        Log.ERROR -> Tree.ERROR_LOGGING_TAG
        else -> throw IllegalArgumentException("${Tree.UNKNOWN_PRIORITY}: $this")
    }

fun FirebaseCrashlytics.recordExceptionWithSuppressed(message: String, t: Throwable) {
    // Since recordException is not taking care of possible suppressed exceptions, we log those
    // manually
    log(message)
    recordException(t)
}