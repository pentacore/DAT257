package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.ProfileBlocks
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProfileBlock(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProfileBlock>(ProfileBlocks)

    var profile by Profile referencedOn ProfileBlocks.profile
    var blockedProfile by Profile referencedOn ProfileBlocks.blockedProfile
    var createdAt by ProfileBlocks.createdAt
    var updatedAt by ProfileBlocks.updatedAt
}