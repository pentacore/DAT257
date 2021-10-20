package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object WalkPathNodes: IntIdTable() {
    val walkPath = reference("walk_path_id", WalkPaths)
    val longitude = double("longitude")
    val latitude = double("latitude")
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
}