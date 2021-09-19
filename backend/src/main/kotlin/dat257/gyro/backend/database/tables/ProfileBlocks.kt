package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object ProfileBlocks: IntIdTable() {
    val profile = reference("profile_id", Profiles)
    val blockedProfile = reference("blocked_profile_id", Profiles)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}