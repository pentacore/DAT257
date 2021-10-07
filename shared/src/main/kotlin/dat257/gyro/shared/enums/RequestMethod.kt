package dat257.gyro.shared.enums

import org.http4k.core.Method

enum class RequestMethod {
    GET, STORE, UPDATE, DELETE;

    fun to4KMethod():Method {
        return when(this) {
            GET -> Method.GET
            STORE -> Method.PUT
            UPDATE -> Method.PATCH
            DELETE -> Method.DELETE
        }
    }

    companion object {
        fun getFrom4KMethod(method: Method): RequestMethod {
            return when (method) {
                Method.GET -> GET
                Method.POST -> STORE
                Method.PUT -> STORE
                Method.DELETE -> DELETE
                Method.PURGE -> DELETE
                Method.PATCH -> UPDATE
                else -> TODO()
            }
        }
    }
}