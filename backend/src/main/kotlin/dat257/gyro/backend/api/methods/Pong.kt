package dat257.gyro.backend.api.methods

import org.http4k.core.Response
import org.http4k.core.Status

fun pong(): Response {
    return Response(Status.OK).body("ping")
}