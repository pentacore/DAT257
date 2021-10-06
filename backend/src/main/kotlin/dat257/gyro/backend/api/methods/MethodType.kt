package dat257.gyro.backend.api.methods

import dat257.gyro.backend.database.models.Profile
import dat257.gyro.shared.ApiHelper.Companion.API_TOKEN_HEADER
import dat257.gyro.shared.ApiHelper.Companion.PROFILE_UUID_HEADER
import org.http4k.core.Request
import org.http4k.core.Response

abstract class MethodType {
    abstract fun get(req: Request):Response
    abstract fun put(req: Request):Response
    abstract fun patch(req: Request):Response
    abstract fun delete(req: Request):Response
}