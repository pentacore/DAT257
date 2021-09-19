package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object WalkHistories: IntIdTable() {
    val profile = reference("profile_id", Profiles)
    val walkPath = reference("walk_path_id", WalkPaths)
    //Personal rating and comments for themselves, e.g "How would you rate your performance"
    val rating = integer("rating")
    val comment = text("comment")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}