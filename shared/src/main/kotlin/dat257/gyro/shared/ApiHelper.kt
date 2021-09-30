package dat257.gyro.shared

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