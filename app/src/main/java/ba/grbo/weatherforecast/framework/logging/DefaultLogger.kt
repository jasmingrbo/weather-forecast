package ba.grbo.weatherforecast.framework.logging

import ba.grbo.core.domain.Logger
import ba.grbo.weatherforecast.BuildConfig
import ba.grbo.weatherforecast.framework.logging.Tree.Companion.DEBUG_TREE
import ba.grbo.weatherforecast.framework.logging.Tree.Companion.RELEASE_TREE
import timber.log.Timber

// Logger is not complete::
// Fix some bugs, implement recursive suppressed and cause exceptions

object DefaultLogger : Logger {

    override fun init() {
        Timber.plant(if (BuildConfig.DEBUG) Timber.DEBUG_TREE else Timber.RELEASE_TREE)
    }

    override fun d(message: String) {
        if (BuildConfig.DEBUG) Timber.d(message)
    }

    override fun e(throwable: Throwable) {
        Timber.e(throwable)
    }
}