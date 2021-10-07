package ba.grbo.weatherforecast.framework.di

import ba.grbo.core.data.AirPollutionSource
import ba.grbo.core.data.LocationSource
import ba.grbo.core.data.PlaceSource
import ba.grbo.core.data.QuerySource
import ba.grbo.core.data.WeatherSource
import ba.grbo.core.data.repositories.AirPollutionRepository
import ba.grbo.core.data.repositories.DefaultAirPollutionRepository
import ba.grbo.core.data.repositories.DefaultLocationRepository
import ba.grbo.core.data.repositories.DefaultPlaceRepository
import ba.grbo.core.data.repositories.DefaultQueryRepository
import ba.grbo.core.data.repositories.DefaultWeatherRepository
import ba.grbo.core.data.repositories.LocationRepository
import ba.grbo.core.data.repositories.PlaceRepository
import ba.grbo.core.data.repositories.QueryRepository
import ba.grbo.core.data.repositories.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class RepositoryProvider {
    @ActivityRetainedScoped
    @Binds
    abstract fun bindPlaceRepository(
        repository: DefaultPlaceRepository,
    ): PlaceRepository

    @ActivityRetainedScoped
    @Binds
    abstract fun bindQueryRepository(
        repository: DefaultQueryRepository,
    ): QueryRepository

    @ActivityRetainedScoped
    @Binds
    abstract fun bindLocationRepository(
        repository: DefaultLocationRepository,
    ): LocationRepository

    @ActivityRetainedScoped
    @Binds
    abstract fun bindWeatherRepository(
        repository: DefaultWeatherRepository,
    ): WeatherRepository

    @ActivityRetainedScoped
    @Binds
    abstract fun bindAirPollutionRepository(
        repository: DefaultAirPollutionRepository,
    ): AirPollutionRepository
}

@InstallIn(ActivityRetainedComponent::class)
@Module
object DefaultRepositoryProvider {
    @ActivityRetainedScoped
    @Provides
    fun provideDefaultPlaceRepository(
        placeSource: PlaceSource,
    ) = DefaultPlaceRepository(placeSource)

    @ActivityRetainedScoped
    @Provides
    fun provideDefaultQueryRepository(
        querySource: QuerySource,
    ) = DefaultQueryRepository(querySource)

    @ActivityRetainedScoped
    @Provides
    fun provideDefaultLocationRepository(
        locationSource: LocationSource,
    ) = DefaultLocationRepository(locationSource)

    @ActivityRetainedScoped
    @Provides
    fun provideDefaultWeatherRepository(
        weatherSource: WeatherSource,
    ) = DefaultWeatherRepository(weatherSource)

    @ActivityRetainedScoped
    @Provides
    fun provideDefaultAirPollutionRepository(
        airPollutionSource: AirPollutionSource,
    ) = DefaultAirPollutionRepository(airPollutionSource)
}