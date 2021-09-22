package dat257.gyro.backend.api

import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes

class Routes {
    companion object {
        val routes = routes(
            "ping" bind Method.GET to { Response(OK).body("pong") },
            "pong" bind Method.GET to { Response(OK).body("ping") }
        )
    }
}