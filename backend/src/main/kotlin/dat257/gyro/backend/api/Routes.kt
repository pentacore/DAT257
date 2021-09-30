package dat257.gyro.backend.api

import dat257.gyro.backend.api.methods.WalkPathEndpoint
import dat257.gyro.backend.api.methods.ping
import dat257.gyro.backend.api.methods.pong
import org.http4k.core.Method
import org.http4k.routing.bind
import org.http4k.routing.routes

class Routes {
    companion object {
        val routes = routes(
            "ping" bind Method.GET to { ping() },
            "pong" bind Method.GET to { pong() },
            "api" bind routes(
                "walkpath" bind routes(
                    Method.GET to { req -> WalkPathEndpoint.get(req) },
                    Method.PUT to { req ->WalkPathEndpoint.put(req) },
                    Method.PATCH to { req ->WalkPathEndpoint.patch(req) },
                    Method.DELETE to { req ->WalkPathEndpoint.delete(req) }
                )
            )
        )
    }
}