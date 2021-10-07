package ba.grbo.weatherforecast.framework.di

import ba.grbo.core.data.repositories.AirPollutionRepository
import ba.grbo.core.data.repositories.LocationRepository
import ba.grbo.core.data.repositories.PlaceRepository
import ba.grbo.core.data.repositories.QueryRepository
import ba.grbo.core.data.repositories.WeatherRepository
import ba.grbo.core.interactors.overview.FetchAirPollution
import ba.grbo.core.interactors.overview.FetchLatestPlace
import ba.grbo.core.interactors.overview.FetchLocations
import ba.grbo.core.interactors.overview.FetchPlace
import ba.grbo.core.interactors.overview.FetchPlaces
import ba.grbo.core.interactors.overview.FetchWeather
import ba.grbo.core.interactors.overview.FetchWeatherAndAirPollution
import ba.grbo.core.interactors.overview.DeletePlace
import ba.grbo.core.interactors.overview.InsertPlace
import ba.grbo.core.interactors.overview.InsertQuery
import ba.grbo.core.interactors.overview.ObserveBasicPlaces
import ba.grbo.core.interactors.overview.ObserveQueries
import ba.grbo.core.interactors.overview.OverviewScreenInteractors
import ba.grbo.core.interactors.overview.RefreshPlace
import ba.grbo.core.interactors.overview.RefreshPlaces
import ba.grbo.core.interactors.overview.UpdatePlace
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ActivityRetainedComponent::class)
@Module
object InteractorsProvider {
    @ActivityRetainedScoped
    @Provides
    fun provideOverviewScreenInteractors(
        observeBasicPlaces: ObserveBasicPlaces,
        observeQueries: ObserveQueries,
        fetchLocations: FetchLocations,
        insertQuery: InsertQuery,
        insertPlace: InsertPlace,
        refreshPlace: RefreshPlace,
        refreshPlaces: RefreshPlaces,
        deletePlace: DeletePlace
    ) = OverviewScreenInteractors(
        observeBasicPlaces = observeBasicPlaces,
        observeQueries = observeQueries,
        fetchLocations = fetchLocations,
        insertQuery = insertQuery,
        insertPlace = insertPlace,
        refreshPlace = refreshPlace,
        refreshPlaces = refreshPlaces,
        deletePlace = deletePlace
    )

    @ActivityRetainedScoped
    @Provides
    fun provideObservePlacesInteractor(repository: PlaceRepository) = ObserveBasicPlaces(repository)

    @ActivityRetainedScoped
    @Provides
    fun provideObserveQueriesInteractor(repository: QueryRepository) = ObserveQueries(repository)

    @ActivityRetainedScoped
    @Provides
    fun provideFetchLocationsInteractor(
        repository: LocationRepository,
        dispatcher: CoroutineDispatcher,
    ) = FetchLocations(repository, dispatcher)

    @ActivityRetainedScoped
    @Provides
    fun provideInsertPlaceInteractor(
        repository: PlaceRepository,
        fetchLatestPlace: FetchLatestPlace,
        updatePlace: UpdatePlace,
        fetchWeatherAndAirPollution: FetchWeatherAndAirPollution,
        dispatcher: CoroutineDispatcher,
    ) = InsertPlace(
        repository,
        fetchLatestPlace,
        updatePlace,
        fetchWeatherAndAirPollution,
        dispatcher
    )

    @ActivityRetainedScoped
    @Provides
    fun provideInsertQueryInteractor(
        repository: QueryRepository,
        dispatcher: CoroutineDispatcher
    ) = InsertQuery(repository, dispatcher)

    @ActivityRetainedScoped
    @Provides
    fun provideFetchLatestPlaceInteractor(
        repository: PlaceRepository
    ) = FetchLatestPlace(repository)

    @ActivityRetainedScoped
    @Provides
    fun provideUpdatePlaceInteractor(
        repository: PlaceRepository
    ) = UpdatePlace(repository)

    @ActivityRetainedScoped
    @Provides
    fun provideFetchPlaceInteractor(
        repository: PlaceRepository
    ) = FetchPlace(repository)

    @ActivityRetainedScoped
    @Provides
    fun provideFetchObservedPlacesInteractor(
        repository: PlaceRepository
    ) = FetchPlaces(repository)

    @ActivityRetainedScoped
    @Provides
    fun provideFetchWeatherInteractor(
        repository: WeatherRepository
    ) = FetchWeather(repository)

    @ActivityRetainedScoped
    @Provides
    fun provideFetchAirPollutionInteractor(
        repository: AirPollutionRepository
    ) = FetchAirPollution(repository)

    @ActivityRetainedScoped
    @Provides
    fun provideFetchWeatherAndAirPollutionInteractor(
        fetchWeather: FetchWeather,
        fetchAirPollution: FetchAirPollution
    ) = FetchWeatherAndAirPollution(fetchWeather, fetchAirPollution)


    @ActivityRetainedScoped
    @Provides
    fun provideRefreshPlaceInteractor(
        fetchPlace: FetchPlace,
        updatePlace: UpdatePlace,
        fetchWeatherAndAirPollution: FetchWeatherAndAirPollution,
        dispatcher: CoroutineDispatcher
    ) = RefreshPlace(
        fetchPlace,
        updatePlace,
        fetchWeatherAndAirPollution,
        dispatcher
    )

    @ActivityRetainedScoped
    @Provides
    fun provideRefreshPlacesInteractor(
        fetchPlaces: FetchPlaces,
        refreshPlace: RefreshPlace,
        dispatcher: CoroutineDispatcher
    ) = RefreshPlaces(
        fetchPlaces,
        refreshPlace,
        dispatcher
    )

    @ActivityRetainedScoped
    @Provides
    fun provideIgnorePlaceInteractor(
        repository: PlaceRepository,
        dispatcher: CoroutineDispatcher
    ) = DeletePlace(repository, dispatcher)
}