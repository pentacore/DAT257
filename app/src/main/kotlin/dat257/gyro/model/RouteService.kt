package dat257.gyro.model

import dat257.gyro.data.remote.HttpResponse
import dat257.gyro.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT

interface RouteService {
    @GET(Constants.BASE_URL)
    suspend fun getRoute(): Response<Route>
    @PUT(Constants.BASE_URL)
    suspend fun putRoute(route: Route): Response<HttpResponse>
}