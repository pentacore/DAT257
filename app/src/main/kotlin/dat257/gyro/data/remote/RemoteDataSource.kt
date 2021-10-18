package dat257.gyro.data.remote

import dat257.gyro.factory.repositoryFactory.DataSource
import dat257.gyro.model.Route
import dat257.gyro.model.RouteService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val routeService: RouteService
): DataSource {
    suspend fun getRoute() = routeService.getRoute()
    suspend fun putRoute(route: Route) = routeService.putRoute(route)
    suspend fun deleteRoute(route: Route) = routeService.deleteRoute(route)
}