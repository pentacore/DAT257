package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.ProfileMessages
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProfileMessage (id: EntityID<Int>) : IntEntity(id) {
companion object : IntEntityClass<ProfileMessage>(ProfileMessages)
    var recipient by Profile referencedOn ProfileMessages.recipient
    var sender by Profile referencedOn ProfileMessages.sender
    var title by ProfileMessages.title
    var message by ProfileMessages.message
    //Sender IP address
    var ipAddress by ProfileMessages.ipAddress
    var createdAt by ProfileMessages.createdAt
    var updatedAt by ProfileMessages.updatedAt
}