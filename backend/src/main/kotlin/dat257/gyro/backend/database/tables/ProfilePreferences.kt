package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object ProfilePreferences: IntIdTable() {
    val profile = reference("profile_id", Profiles)
    val showOnMap = text("show_on_map")
    val allowFriendRequests = bool("allow_friend_requests")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}