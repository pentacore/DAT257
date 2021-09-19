package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.ProfilePreferences
import dat257.gyro.backend.database.types.ShowOnMap
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProfilePreference (id: EntityID<Int>) : IntEntity(id) {
companion object : IntEntityClass<ProfilePreference>(ProfilePreferences)
    var profile by Profile referencedOn ProfilePreferences.profile
    var showOnMap by ProfilePreferences.showOnMap.transform({it.value},{ ShowOnMap.valueOf(it.toString())})
    var allowFriendRequests by ProfilePreferences.allowFriendRequests
    var createdAt by ProfilePreferences.createdAt
    var updatedAt by ProfilePreferences.updatedAt
}