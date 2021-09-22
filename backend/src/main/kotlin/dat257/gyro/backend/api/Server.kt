package dat257.gyro.backend.api

import org.http4k.server.Http4kServer
import org.http4k.server.Netty
import org.http4k.server.asServer

class Server {
    companion object {
        fun get(): Http4kServer {
            val port:Int = (System.getenv("API_PORT") ?: "9000").toInt()
            return Routes.routes.asServer(Netty(port));
        }
    }
}