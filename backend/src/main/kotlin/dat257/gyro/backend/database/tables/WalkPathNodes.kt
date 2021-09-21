package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object WalkPathNodes: IntIdTable() {
    val walkPath = reference("walk_path_id", WalkPaths)
    val coordinates = text("coordinates")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}