package dat257.gyro.backend.database.tables

import dat257.gyro.backend.database.tables.WalkPathNodes.default
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object WalkPaths: IntIdTable() {
    val profile = reference("profile_id", Profiles)
    val name = text("name")
    val description = text("description")
    val public = bool("public")
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
}