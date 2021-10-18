package dat257.gyro.data.remote

import kotlinx.serialization.Serializable
@Serializable
data class HttpResponse(
    val message: String,
    val statusCode: String
) {

}
