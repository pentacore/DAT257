package dat257.gyro.backend.database.tables

import dat257.gyro.backend.database.tables.WalkPathNodes.default
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

// TODO: Set the ID column length to the correct hwid length
object Devices : IntIdTable() {
    var hwid = text("hwid")
    val profile = reference("profile_id", Profiles)
    val description = text("description")
    val model = varchar("model", 50)
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
}