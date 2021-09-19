package dat257.gyro.backend.database.tables

import dat257.gyro.database.models.WalkPathClassification
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.`java-time`.datetime

object WalkPaths: IntIdTable() {
    val profile = reference("profile_id", Profiles)
    val name = text("name")
    val description = text("description")
    val public = bool("public")
    val classification = reference("id", Column<Int>(WalkPathClassifications, "walk_path_id", IntegerColumnType()))
    val rating = integer("rating")
    val comment = text("comment")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}