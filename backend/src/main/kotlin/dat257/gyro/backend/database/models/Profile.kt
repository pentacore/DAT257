package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.Profiles
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Profile(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Profile>(Profiles)

    var uuid by Profiles.uuid
    var name by Profiles.name
    var avatar by Profiles.avatar
    var banned by Profiles.banned
    var createdAt by Profiles.createdAt
    var updatedAt by Profiles.updatedAt
}