package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object Profiles: IntIdTable() {
    val uuid = uuid("uuid")
    val name = text("name")
    val avatar = text("avatar")
    val banned = bool("banned")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}