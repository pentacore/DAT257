package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object PictureRatings: IntIdTable() {
    val profile = reference("profile_id", Profiles)
    val picture = reference("picture_id", Pictures)
    val rating = integer("rating")
    val comment = text("comment")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}