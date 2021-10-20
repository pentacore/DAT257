package dat257.gyro.backend.database.tables

import dat257.gyro.backend.database.tables.WalkPathNodes.default
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object WalkHistories: IntIdTable() {
    val profile = reference("profile_id", Profiles)
    val walkPath = reference("walk_path_id", WalkPaths)
    //Personal rating and comments for themselves, e.g "How would you rate your performance"
    val rating = integer("rating")
    val comment = text("comment")
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
}