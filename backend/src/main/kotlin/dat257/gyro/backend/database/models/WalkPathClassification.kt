package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.WalkPathClassifications
import dat257.gyro.shared.enums.AreaType
import dat257.gyro.shared.enums.PathType
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import dat257.gyro.shared.dataTypes.WalkPath as SWalkPath

class WalkPathClassification(id: EntityID<Int>) : IntEntity(id) {
    fun toSharedModel(): SWalkPath.Classification {
        return SWalkPath.Classification(
            pathType, areaType, handicapFriendly, strollerFriendly, lighted, LocalDateTime.parse(createdAt.toString()), LocalDateTime.parse(updatedAt.toString())
        )
    }

    companion object : IntEntityClass<WalkPathClassification>(WalkPathClassifications)

    var walkPath by WalkPathClassifications.walkPath
    var pathType by WalkPathClassifications.pathType.transform({ it.type }, { PathType.valueOf(it.toString()) })
    var areaType by WalkPathClassifications.areaType.transform({ it.type }, { AreaType.valueOf(it.toString()) })
    var handicapFriendly by WalkPathClassifications.handicapFriendly
    var strollerFriendly by WalkPathClassifications.strollerFriendly
    var lighted by WalkPathClassifications.lighted
    var createdAt by WalkPathClassifications.createdAt
    var updatedAt by WalkPathClassifications.updatedAt
}