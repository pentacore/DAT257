package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object WalkPathClassifications: IntIdTable() {
    val walkPath = reference("walk_path_id", WalkPaths).uniqueIndex()
    val pathType = text("path_type")
    val areaType = text("area_type")
    val handicapFriendly = bool("handicap_friendly")
    val strollerFriendly = bool("stroller_friendly")
    val lighted = bool("lighted")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}