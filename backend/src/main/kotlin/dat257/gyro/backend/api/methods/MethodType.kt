package dat257.gyro.backend.api.methods

import org.http4k.core.Request
import org.http4k.core.Response

abstract class MethodType {
    abstract fun get(req: Request):Response
    abstract fun put(req: Request):Response
    abstract fun patch(req: Request):Response
    abstract fun delete(req: Request):Response
}