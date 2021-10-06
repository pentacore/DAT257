package dat257.gyro.backend.helpers

import dat257.gyro.backend.database.DatabaseClient.Companion.loggedTransaction
import dat257.gyro.backend.database.models.Profile
import dat257.gyro.backend.database.tables.Profiles
import dat257.gyro.shared.ApiHelper
import org.http4k.core.Request
import java.util.*

abstract class EndpointHelper {
    protected fun getProfileFromRequest(req: Request): Profile {
        val token = req.header(ApiHelper.API_TOKEN_HEADER)
        val uuid = UUID.fromString(req.header(ApiHelper.PROFILE_UUID_HEADER))

        return loggedTransaction {
            Profile.find {
                Profiles.uuid eq uuid
            }.limit(1).first()
        }
    }
}