package dat257.gyro

import dat257.gyro.backend.api.Routes
import dat257.gyro.backend.database.DatabaseClient
import dat257.gyro.backend.database.DatabaseClient.Companion.loggedTransaction
import dat257.gyro.backend.database.models.*
import dat257.gyro.shared.enums.AreaType
import dat257.gyro.shared.enums.PathType
import org.junit.Before
import java.util.*

open class BaseTest {
    protected val app = Routes.routes
    protected lateinit var db: DatabaseClient
    protected var profile: Profile? = null
    protected var device: Device? = null
    protected var pathCounter = 0;
    protected val rand = Random()

    companion object {
        const val DEFAULT_USER_PATH_NUM = 3
        const val DEFAULT_USER_PATH_NODES_NUM = 5
        const val DEFAULT_SECONDARY_USERS_NUM = 5
        const val DEFAULT_SECONDARY_USERS_PUBLIC_PATH_NUM = 3
        const val DEFAULT_SECONDARY_USERS_PRIVATE_PATH_NUM = 3
    }

    @Before
    fun initDb() {
        db = DatabaseClient(tempDb = true, migrate = true)
    }

    protected fun createUser(profileUUID: UUID? = null, deviceUUID: UUID? = null) {
        loggedTransaction {
            profile = Profile.new {
                this.name = "Test Testsson"
                this.uuid = profileUUID ?: UUID.randomUUID()
                this.banned = false
                this.avatar = ""
            }

            device = Device.new {
                this.description = "Test device for user #${this@BaseTest.profile!!.id.value}"
                this.hwid = (deviceUUID ?: UUID.randomUUID()).toString()
                this.model = "Testphone 3000"
                this.profile = this@BaseTest.profile!!
            }
        }
    }

    protected fun createSecondaryUsers(num: Int = DEFAULT_SECONDARY_USERS_NUM) {
        loggedTransaction {
            for (i in 1..num) {
                val profile = Profile.new {
                    this.name = "Test Testsson"
                    this.uuid = UUID.randomUUID()
                    this.banned = false
                    this.avatar = ""
                }

                Device.new {
                    this.description = "Test device for user #${profile.id.value}"
                    this.hwid = UUID.randomUUID().toString()
                    this.model = "Testphone ${3000 * i}"
                    this.profile = profile
                }

                createTestPath(num = DEFAULT_SECONDARY_USERS_PUBLIC_PATH_NUM, public = true, profile = profile)
                createTestPath(num = DEFAULT_SECONDARY_USERS_PRIVATE_PATH_NUM, public = false, profile = profile)
            }
        }
    }

    protected fun createTestPath(num: Int = DEFAULT_USER_PATH_NUM, numNodes: Int = DEFAULT_USER_PATH_NODES_NUM, public: Boolean = false, profile: Profile? = this.profile) {
        if (profile == null) {
            throw Exception("Initialize the profile before creating WalkPaths")
        }

        loggedTransaction {
            for (i in 1..num) {
                val p = WalkPath.new {
                    this.profile = profile
                    this.name = "WalkPath #${++pathCounter}"
                    this.description = "Super descriptive thing"
                    this.public = public
                }

                val c = WalkPathClassification.new {
                    this.areaType = AreaType.NONE
                    this.pathType = PathType.NONE
                    this.handicapFriendly = false
                    this.strollerFriendly = false
                    this.lighted = false
                    this.walkPath = p
                }

                for (j in 1..numNodes) {
                    WalkPathNode.new {
                        this.walkPath = p
                        this.longitude = 1.1 * j
                        this.latitude = 2.9 * j
                    }
                }
                commit()
            }
        }
    }
}