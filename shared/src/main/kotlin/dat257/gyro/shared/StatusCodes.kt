package dat257.gyro.shared

enum class StatusCode(code: Int) {
    OK(200),
    BadRequest(400),
    Unauthorized(401),
    Forbidden(403),
    NotFound(404),
    MethodNotAllowed(405),
    InternalServerError(500),
    NotImplemented(501),
    Unknown(0)
}