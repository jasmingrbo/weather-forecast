package ba.grbo.weatherforecast.framework.di

import ba.grbo.weatherforecast.framework.data.dto.remote.locationiq.adapters.AddressAdapter
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.adapters.AirPollutionDataAdapter
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.adapters.AlertAdapter
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.adapters.IconAdapter
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.adapters.DescriptionAndIconAdapter
import ba.grbo.weatherforecast.framework.data.dto.remote.openweather.adapters.PrecipitationAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Qualifier

@InstallIn(ActivityRetainedComponent::class)
@Module
object MoshiAdapterProvider {
    @ActivityRetainedScoped
    @LocationIQWeatherMoshi
    @Provides
    fun provideLocationIQMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(AddressAdapter)
        .build()

    @ActivityRetainedScoped
    @OpenWeatherMoshi
    @Provides
    fun provideOpenWeatherMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(PrecipitationAdapter)
        .add(IconAdapter)
        .add(DescriptionAndIconAdapter)
        .add(AlertAdapter)
        .add(AirPollutionDataAdapter)
        .build()

    @ActivityRetainedScoped
    @BasicMoshi
    @Provides
    fun provideBasicMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OpenWeatherMoshi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocationIQWeatherMoshi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BasicMoshi