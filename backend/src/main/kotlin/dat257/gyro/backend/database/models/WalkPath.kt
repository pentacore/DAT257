package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.WalkPaths
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class WalkPath(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<WalkPath>(WalkPaths)

    var profile by Profile referencedOn WalkPaths.profile
    var name by WalkPaths.name
    var description by WalkPaths.description
    var public by WalkPaths.public
    var classification by WalkPathClassification referencedOn WalkPaths.profile
    var createdAt by WalkPaths.createdAt
    var updatedAt by WalkPaths.updatedAt
}