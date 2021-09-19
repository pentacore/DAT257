package dat257.gyro.backend.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object ProfileMessages: IntIdTable() {
    val recipient = reference("profile_id", Profiles)
    val sender = reference("sender", Profiles)
    val message = text("message")
    //Sender IP address
    val ipAddress = text("ip_address")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}