package dat257.gyro.data

import android.graphics.Bitmap
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dat257.gyro.data.local.dao.SettingsDao
import dat257.gyro.data.remote.HttpResponse
import dat257.gyro.data.remote.RemoteDataSource
import dat257.gyro.model.BaseApiResponse
import dat257.gyro.model.Route
import dat257.gyro.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    //private val dao: SettingsDao
) : BaseApiResponse() {

    suspend fun getRoute(): Flow<NetworkResult<Route>> =
        flow<NetworkResult<Route>> {
            emit(safeApiCall { remoteDataSource.getRoute() })
        }.flowOn(Dispatchers.IO)

    suspend fun putRoute(route: Route): Flow<NetworkResult<HttpResponse>> =
        flow<NetworkResult<HttpResponse>> {
            emit(safeApiCall { remoteDataSource.putRoute(route) })
        }.flowOn(Dispatchers.IO)

    fun saveImage(image: Bitmap, storageDir: File, imageFileName: String): Flow<Boolean> {

        val successDirCreated = if (!storageDir.exists()) {
            storageDir.mkdir()
        } else {
            true
        }

        if (successDirCreated) {
            val imageFile = File(storageDir, imageFileName)
            return try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
                flow {
                    emit(true)
                }.flowOn(Dispatchers.IO)
            } catch (e: Exception) {
                e.printStackTrace()
                flow {
                    emit(false)
                }.flowOn(Dispatchers.IO)
            }
        } else {
            return flow { emit(false) }.flowOn(Dispatchers.IO)
        }
    }

}