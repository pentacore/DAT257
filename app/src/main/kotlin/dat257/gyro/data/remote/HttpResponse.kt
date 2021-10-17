package dat257.gyro.data.remote

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import com.google.android.gms.common.api.CommonStatusCodes

@Serializable
data class HttpResponse(
    val message: String,
    val statusCode: String
) {

}
