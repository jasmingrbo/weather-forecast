package ba.grbo.weatherforecast.framework.logging

import android.util.Log
import ba.grbo.weatherforecast.BuildConfig

object Message {
    private val DEBUG_MESSAGE_PATTERN = StringBuilder().apply {
        append("message: %s\n  ")
        append("info:    %s\n  ")
        append("thread:  %s\n ")
    }.toString()

    private val ERROR_MESSAGE_PATTERN = object {
        val DEBUG = StringBuilder().apply {
            append("exception:  %s\n  ")
            append("message:    %s\n  ")
            append("info:       %s\n  ")
            append("thread:     %s\n  ")
            append("suppressed: %s\n ")
        }.toString()

        val RELEASE = "exception: %s, message: %s, info: %s, thread: %s, suppressed: %s"
    }

    private const val COUNTER_MESSAGE_PATTERN = "%d. %s"

    fun create(
        priority: Int,
        message: String,
        throwable: Throwable?,
        element: StackTraceElement
    ) = if (BuildConfig.DEBUG) String.format(
        COUNTER_MESSAGE_PATTERN,
        Counter.getAndIncrement(priority),
        if (priority == Log.DEBUG) createDebugMessage(message, element)
        else createErrorMessage(throwable, element)
    ) else createErrorMessage(throwable, element, ERROR_MESSAGE_PATTERN.RELEASE)

    private fun createDebugMessage(
        message: String,
        element: StackTraceElement,
    ) = String.format(
        DEBUG_MESSAGE_PATTERN,
        message,
        element.info,
        Thread.currentThread().name
    )

    private fun createErrorMessage(
        throwable: Throwable?,
        element: StackTraceElement,
        messagePattern: String = ERROR_MESSAGE_PATTERN.DEBUG
    ) = String.format(
        messagePattern,
        throwable.simpleClassNameOrNone,
        throwable.messageOrNone,
        element.info,
        Thread.currentThread().name,
        throwable.suppressedInfoOrNone
    )
}