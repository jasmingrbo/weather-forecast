package ba.grbo.weatherforecast.framework.di

import ba.grbo.weatherforecast.framework.data.source.remote.locationiq.LocationIQService
import ba.grbo.weatherforecast.framework.data.source.remote.openweather.OpenWeatherService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(ActivityRetainedComponent::class)
@Module
object RemoteServiceProvider {
    @ActivityRetainedScoped
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
        .protocols(listOf(Protocol.HTTP_1_1))
        .build()

    @ActivityRetainedScoped
    @Provides
    fun provideLocationIQService(
        client: OkHttpClient,
        @LocationIQWeatherMoshi moshi: Moshi,
    ): LocationIQService = synchronized(this) {
        Retrofit.Builder()
            .baseUrl(LocationIQService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(LocationIQService::class.java)
    }

    @ActivityRetainedScoped
    @Provides
    fun provideOpenWeatherService(
        client: OkHttpClient,
        @OpenWeatherMoshi moshi: Moshi,
    ): OpenWeatherService = synchronized(this) {
        Retrofit.Builder()
            .baseUrl(OpenWeatherService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(OpenWeatherService::class.java)
    }
}