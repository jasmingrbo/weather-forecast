package ba.grbo.weatherforecast.framework.mics

import android.app.Application
import ba.grbo.weatherforecast.framework.logging.DefaultLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherForecastApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DefaultLogger.init()
    }
}