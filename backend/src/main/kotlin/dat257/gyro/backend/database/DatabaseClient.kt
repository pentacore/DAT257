package dat257.gyro.backend.database

import dat257.gyro.backend.database.tables.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseClient(
    private val host: String = System.getenv("POSTGRES_HOST") ?: "127.0.0.1",
    private val port: String = System.getenv("POSTGRES_PORT") ?: "5432",
    private val name: String = System.getenv("POSTGRES_NAME") ?: "gyro",
    private val user: String = System.getenv("POSTGRES_USER") ?: "gyro",
    private val password: String = System.getenv("POSTGRES_PASS") ?: "gyro",
    private val tempDb: Boolean = (System.getenv("TEMP_DB") ?: "false").lowercase() == "true",
    private val migrate: Boolean = (System.getenv("RUN_DB_MIGRATIONS") ?: "true").lowercase() == "true"
) {
    private val tables = arrayOf(
        Devices, PictureRatings, Pictures, ProfileBlocks, ProfileFriends, ProfileMessages, ProfilePreferences,
        Profiles, WalkHistories, WalkPathClassifications, WalkPathNodes, WalkPathRatings, WalkPaths
    )
    private val db = connect()

    private fun connect(): Database {
        val db = if (tempDb) {
            Database.connect("jdbc:h2:mem:$name;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver", user = user)
        } else {
            Database.connect("jdbc:postgresql://$host:$port/$name", driver = "org.postgresql.Driver", user = user, password = password)
        }

        if (migrate) {
            loggedTransaction {
                SchemaUtils.create(*tables)
            }
        }

        return db
    }

    fun getConnection(): Database {
        return db;
    }

    companion object {
        /**
         * Use this to get a transaction with the standard logging interface added.
         */
        fun <T> loggedTransaction(db: Database? = null, statement: Transaction.() -> T): T {
            return transaction(db) {
                addLogger(StdOutSqlLogger)
                statement()
            }
        }
    }
}