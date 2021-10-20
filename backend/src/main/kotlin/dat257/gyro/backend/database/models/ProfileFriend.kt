package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.ProfileFriends
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProfileFriend(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProfileFriend>(ProfileFriends)

    var profile1 by Profile referencedOn ProfileFriends.profile1
    var profile2 by Profile referencedOn ProfileFriends.profile2
    var pending by ProfileFriends.pending
    var createdAt by ProfileFriends.createdAt
    var updatedAt by ProfileFriends.updatedAt
}