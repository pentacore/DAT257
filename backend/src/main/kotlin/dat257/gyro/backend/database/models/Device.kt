package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.Devices
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Device (id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, Device>(Devices)

    var profile by Profile referencedOn Devices.profile
    var description by Devices.description
    var model by Devices.model
    var createdAt by Devices.createdAt
    var updatedAt by Devices.updatedAt
}