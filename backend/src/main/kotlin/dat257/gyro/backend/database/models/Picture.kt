package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.Pictures
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Picture(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Picture>(Pictures)

    var profile by Pictures.profile
    var title by Pictures.title
    var description by Pictures.description
    var coordinates by Pictures.coordinates
    var createdAt by Pictures.createdAt
    var updatedAt by Pictures.updatedAt
}