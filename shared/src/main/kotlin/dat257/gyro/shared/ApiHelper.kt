package dat257.gyro.shared

class ApiHelper {
    companion object {
        fun getApiUrl(endpoint: String? = null): String {
            var url = System.getenv("API_ENDPOINT_URL") ?: "localhost:9000"
            if (endpoint != null) {
                if (endpoint[0] != '/') {
                    url += "/"
                }
                url += endpoint
            }
            return url;
        }
    }
}