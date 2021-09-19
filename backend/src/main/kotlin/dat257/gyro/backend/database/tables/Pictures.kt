package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object Pictures: IntIdTable() {
    val profile = reference("profile_id", Profiles)
    val title = text("title")
    val description = text("description")
    val coordinates = text("coordinates")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}