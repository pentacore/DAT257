package dat257.gyro.backend.api.methods

import org.http4k.core.Response
import org.http4k.core.Status

fun ping(): Response {
    return Response(Status.OK).body("pong")
}