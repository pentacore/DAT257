package dat257.gyro

import dat257.gyro.backend.api.Routes
import dat257.gyro.backend.database.DatabaseClient

open class BaseTest {
    protected val app = Routes.routes
    protected val db = DatabaseClient(tempDb=true, migrate = true)
}