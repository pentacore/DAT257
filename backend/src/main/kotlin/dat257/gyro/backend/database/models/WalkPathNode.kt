package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.WalkPathNodes
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class WalkPathNode (id: EntityID<Int>) : IntEntity(id) {
companion object : IntEntityClass<WalkPathNode>(WalkPathNodes)
    var walkPath by WalkPath referencedOn WalkPathNodes.walkPath
    var coordinates by WalkPathNodes.coordinates
    var createdAt by WalkPathNodes.createdAt
    var updatedAt by WalkPathNodes.updatedAt
}