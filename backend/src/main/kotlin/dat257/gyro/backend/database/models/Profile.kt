package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.DatabaseClient.Companion.loggedTransaction
import dat257.gyro.backend.database.tables.Profiles
import dat257.gyro.backend.database.tables.WalkPaths
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op

class Profile(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Profile>(Profiles)

    var uuid by Profiles.uuid
    var name by Profiles.name
    var avatar by Profiles.avatar
    var banned by Profiles.banned
    var createdAt by Profiles.createdAt
    var updatedAt by Profiles.updatedAt

    fun getWalkPaths(): List<WalkPath> {
        return loggedTransaction {
            WalkPath.find {
                Op.build { WalkPaths.profile eq this@Profile.id }
            }.toList()
        }
    }

    fun getWalkPathsSharedModel(): List<dat257.gyro.shared.dataTypes.WalkPath> {
        return loggedTransaction {
            val t = getWalkPaths()
            val r = emptyList<dat257.gyro.shared.dataTypes.WalkPath>().toMutableList()
            for (wp in t) {
                r.add(wp.toSharedModel())
            }
            r.toList()
        }
    }
}