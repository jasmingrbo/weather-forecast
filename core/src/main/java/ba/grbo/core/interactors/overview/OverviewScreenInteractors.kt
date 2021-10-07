package ba.grbo.core.interactors.overview

data class OverviewScreenInteractors(
    val observeBasicPlaces: ObserveBasicPlaces,
    val observeQueries: ObserveQueries,
    val fetchLocations: FetchLocations,
    val insertQuery: InsertQuery,
    val insertPlace: InsertPlace,
    val refreshPlace: RefreshPlace,
    val refreshPlaces: RefreshPlaces,
    val deletePlace: DeletePlace
)
