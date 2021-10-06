package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.DatabaseClient.Companion.loggedTransaction
import dat257.gyro.backend.database.tables.WalkPathNodes
import dat257.gyro.backend.database.tables.WalkPaths
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import dat257.gyro.shared.dataTypes.Profile as SProfile
import dat257.gyro.shared.dataTypes.WalkPath as SWalkPath

class WalkPath(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<WalkPath>(WalkPaths) {
        fun getPublicWalkPaths(excludeUserId: Int? = null): List<WalkPath> {
            return loggedTransaction {
                when (excludeUserId) {
                    null -> WalkPath.find(
                        Op.build { WalkPaths.public eq true }
                    )
                    else -> WalkPath.find(
                        Op.build { WalkPaths.public eq true } and Op.build { WalkPaths.profile neq excludeUserId }
                    )
                }
            }.toList()
        }
    }

    var profile by Profile referencedOn WalkPaths.profile
    var name by WalkPaths.name
    var description by WalkPaths.description
    var public by WalkPaths.public
    var classification by WalkPathClassification referencedOn WalkPaths.profile
    var createdAt by WalkPaths.createdAt
    var updatedAt by WalkPaths.updatedAt

    fun toSharedModel(): SWalkPath {
        val sClassification = classification.toSharedModel()
        val nodes = loggedTransaction {
            val n = WalkPathNode.find {
                Op.build { WalkPathNodes.walkPath eq this@WalkPath.id }
            }
            val nArray = emptyList<dat257.gyro.shared.dataTypes.WalkPath.Node>().toMutableList()
            for (node in n) {
                nArray.add(node.toSharedModel())
            }
            nArray.toTypedArray()
        }
        return SWalkPath(
            //TODO add ratings and fix owner field
            id.value, name, description, public, sClassification, nodes, null, SProfile(name = profile.name), LocalDateTime.parse(createdAt.toString()), LocalDateTime.parse(updatedAt.toString())
        )
    }
}