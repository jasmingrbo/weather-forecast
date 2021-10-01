package ba.grbo.weatherforecast.framework.logging

import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

abstract class Tree : Timber.DebugTree() {
    protected lateinit var element: StackTraceElement

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, priority.tag, message, t)
    }

    override fun createStackElementTag(element: StackTraceElement): String? {
        // We call next because Timber calls are wrapped inside of DefaultLogger
        this.element = element.next
        return null
    }

    companion object {
        val Timber.Forest.DEBUG_TREE: Tree
            get() = object : Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    super.log(priority, tag, Message.create(priority, message, t, element), t)
                }
            }

        val Timber.Forest.RELEASE_TREE: Tree
            get() = object : Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    if (t != null) FirebaseCrashlytics.getInstance().recordExceptionWithSuppressed(
                        Message.create(priority, message, t, element),
                        t
                    )
                }
            }

        const val UNKNOWN_PRIORITY = "Unknown priority"
        const val EMPTY_MESSAGE = "None"

        private const val COMMON_LOGGING_TAG = "ba.grbo"
        const val DEBUG_LOGGING_TAG = "$COMMON_LOGGING_TAG.debug"
        const val ERROR_LOGGING_TAG = "$COMMON_LOGGING_TAG.error"
    }
}