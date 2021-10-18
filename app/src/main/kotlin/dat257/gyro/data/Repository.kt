package dat257.gyro.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import dat257.gyro.data.local.AbstractDb
import dat257.gyro.data.local.UserSettings
import dat257.gyro.data.remote.HttpResponse
import dat257.gyro.data.remote.RemoteDataSource
import dat257.gyro.model.BaseApiResponse
import dat257.gyro.model.Route
import dat257.gyro.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val database: AbstractDb
) : BaseApiResponse() {

    suspend fun getRoute(): Flow<NetworkResult<Route>> =
        flow<NetworkResult<Route>> {
            emit(safeApiCall { remoteDataSource.getRoute() })
        }.flowOn(Dispatchers.IO)

    suspend fun putRoute(route: Route): Flow<NetworkResult<HttpResponse>> =
        flow<NetworkResult<HttpResponse>> {
            emit(safeApiCall { remoteDataSource.putRoute(route) })
        }.flowOn(Dispatchers.IO)

    suspend fun getSettings(): Flow<UserSettings> = flow {
        emit(
            database.settingsDao().getSettings()[0]
        )
    }.flowOn(Dispatchers.IO)

    suspend fun updateSettings(settings: UserSettings): Flow<Unit> = flow {
        emit(
            database.settingsDao().updateSetting(settings)
        )
    }.flowOn(Dispatchers.IO)

    suspend fun getSetting(setting: String): Flow<List<String>> = flow {
        emit(
            database.settingsDao().getSetting(setting)
        )
    }
}