package dat257.gyro.backend.database.tables

import dat257.gyro.backend.helpers.StringIdTable
import org.jetbrains.exposed.sql.javatime.datetime

// TODO: Set the ID column length to the correct hwid length
object Devices : StringIdTable("hwid", "hwid", 15) {
    val profile = reference("profile_id", Profiles)
    val description = text("description")
    val model = varchar("model", 50)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}