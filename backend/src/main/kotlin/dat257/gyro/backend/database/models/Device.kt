package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.Devices
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Device(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Device>(Devices)

    var hwid by Devices.hwid
    var profile by Profile referencedOn Devices.profile
    var description by Devices.description
    var model by Devices.model
    var createdAt by Devices.createdAt
    var updatedAt by Devices.updatedAt
}