package dat257.gyro.shared

import dat257.gyro.shared.types.TransferType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class ApiHelper {
    companion object {
        const val API_TOKEN_HEADER = "x-jwt-token"
        const val PROFILE_UUID_HEADER = "x-profile-uuid"

        fun getApiUrl(endpoint: String? = null): String {
            var url = System.getenv("API_ENDPOINT_URL") ?: "http://localhost:9000"
            if (endpoint != null) {
                if (endpoint[0] != '/') {
                    url += "/"
                }
                url += endpoint
            }
            return url;
        }

        fun getApiToken(): String {
            // TODO
            return ""
        }

        fun getProfileUUID(): String {
            // TODO
            return ""
        }
    }
}

fun String.Companion.notNullOrEmpty(string:String?):Boolean {
    return string != null && string != ""
}

fun Int.Companion.notNullAndGTZero(int: Int?): Boolean {
    return int != null && int > 0;
}

typealias Longitude = Double
typealias Latitude = Double