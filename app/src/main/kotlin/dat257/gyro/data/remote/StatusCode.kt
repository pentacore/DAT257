package dat257.gyro.data.remote

import kotlinx.serialization.Serializable

sealed class StatusCode(code : Int) {
    object StatusOk : StatusCode(200)
    object NotFound : StatusCode(404)
    object InternalServerError : StatusCode(400)
}
