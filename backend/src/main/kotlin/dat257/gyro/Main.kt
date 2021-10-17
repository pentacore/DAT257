package dat257.gyro

import dat257.gyro.backend.api.Server
import dat257.gyro.backend.database.DatabaseClient

fun main() {
    val database = DatabaseClient()
    val server = Server.get().start()
}