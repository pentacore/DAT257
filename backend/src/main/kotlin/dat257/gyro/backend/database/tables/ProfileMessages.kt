package dat257.gyro.backend.database.tables

import dat257.gyro.backend.database.tables.WalkPathNodes.default
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object ProfileMessages: IntIdTable() {
    val recipient = reference("profile_id", Profiles)
    val sender = reference("sender", Profiles)
    val title = text("title")
    val message = text("message")
    //Sender IP address
    val ipAddress = text("ip_address")
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
}