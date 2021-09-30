package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.WalkHistories
import dat257.gyro.shared.types.Rating
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class WalkHistory (id: EntityID<Int>) : IntEntity(id) {
companion object : IntEntityClass<WalkHistory>(WalkHistories)
    var profile by Profile referencedOn WalkHistories.profile
    var walkPath by WalkPath referencedOn WalkHistories.walkPath
    //Personal rating and comments for themselves, e.g "How would you rate your performance"
    var rating by WalkHistories.rating.transform({it.value},{ Rating.valueOf(it.toString())})
    var comment by WalkHistories.comment
    var createdAt by WalkHistories.createdAt
    var updatedAt by WalkHistories.updatedAt
}