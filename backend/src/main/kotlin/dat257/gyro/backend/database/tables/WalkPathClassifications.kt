package dat257.gyro.backend.database.tables

import dat257.gyro.shared.enums.AreaType
import dat257.gyro.shared.enums.PathType
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object WalkPathClassifications: IntIdTable() {
    val walkPath = reference("walk_path_id", WalkPaths).uniqueIndex()
    val pathType = text("path_type").default(PathType.NONE.type)
    val areaType = text("area_type").default(AreaType.NONE.type)
    val handicapFriendly = bool("handicap_friendly")
    val strollerFriendly = bool("stroller_friendly")
    val lighted = bool("lighted")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}