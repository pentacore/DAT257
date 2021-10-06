package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object ProfileFriends: IntIdTable() {
    val profile1 = reference("profile_1_id", Profiles)
    val profile2 = reference("profile_2_id", Profiles)
    val pending = bool("pending")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}