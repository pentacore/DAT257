package dat257.gyro.backend.api

import dat257.gyro.backend.api.methods.WalkPathEndpoint
import dat257.gyro.backend.api.methods.ping
import dat257.gyro.backend.api.methods.pong
import org.http4k.core.Method.*
import org.http4k.routing.bind
import org.http4k.routing.routes

class Routes {
    companion object {
        val routes = routes(
            "ping" bind GET to { ping() },
            "pong" bind GET to { pong() },
            "api" bind routes(
                "walkpath" bind routes(
                    GET to { req -> WalkPathEndpoint.get(req) },
                    PUT to { req ->WalkPathEndpoint.put(req) },
                    PATCH to { req ->WalkPathEndpoint.patch(req) },
                    DELETE to { req ->WalkPathEndpoint.delete(req) }
                )
            )
        )
    }
}