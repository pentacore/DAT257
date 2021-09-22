package dat257.gyro

import dat257.gyro.backend.api.Routes
import dat257.gyro.backend.database.DatabaseClient
import org.junit.Before

open class BaseTest {
    protected val app = Routes.routes
    protected val db = DatabaseClient(tempDb=true, migrate = true)
}