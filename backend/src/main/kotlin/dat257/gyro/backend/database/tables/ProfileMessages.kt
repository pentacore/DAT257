package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object ProfileMessages: IntIdTable() {
    val recipient = reference("profile_id", Profiles)
    val sender = reference("sender", Profiles)
    val title = text("title")
    val message = text("message")
    //Sender IP address
    val ipAddress = text("ip_address")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}