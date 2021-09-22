package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.`java-time`.datetime

object WalkPaths: IntIdTable() {
    val profile = reference("profile_id", Profiles)
    val name = text("name")
    val description = text("description")
    val public = bool("public")
    val classification = reference("classification_id", Column<Int>(WalkPathClassifications, "walk_path_id", IntegerColumnType())).uniqueIndex()
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}