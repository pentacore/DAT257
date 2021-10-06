package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.WalkPathNodes
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import dat257.gyro.shared.dataTypes.WalkPath as SWalkPath

class WalkPathNode (id: EntityID<Int>) : IntEntity(id) {
    fun toSharedModel(): SWalkPath.Node {
        return SWalkPath.Node (longitude,latitude,LocalDateTime.parse(createdAt.toString()), LocalDateTime.parse(updatedAt.toString()))
    }

    companion object : IntEntityClass<WalkPathNode>(WalkPathNodes)
    var walkPath by WalkPath referencedOn WalkPathNodes.walkPath
    var longitude by WalkPathNodes.longitude
    var latitude by WalkPathNodes.latitude
    var createdAt by WalkPathNodes.createdAt
    var updatedAt by WalkPathNodes.updatedAt
}