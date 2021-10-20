package dat257.gyro.backend.database.tables

import dat257.gyro.backend.database.tables.WalkPathNodes.default
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object ProfilePreferences: IntIdTable() {
    val profile = reference("profile_id", Profiles)
    val showOnMap = text("show_on_map")
    val allowFriendRequests = bool("allow_friend_requests")
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
}