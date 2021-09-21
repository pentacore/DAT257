package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.WalkPathRatings
import dat257.gyro.backend.database.types.Ratings
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class WalkPathRating (id: EntityID<Int>) : IntEntity(id) {
companion object : IntEntityClass<WalkPathRating>(WalkPathRatings)
    var profile by Profile referencedOn WalkPathRatings.profile
    var walkPath by WalkPath referencedOn WalkPathRatings.walkPath
    var rating by WalkPathRatings.rating.transform({it.value},{ Ratings.valueOf(it.toString())})
    var comment by WalkPathRatings.comment
    var createdAt by WalkPathRatings.createdAt
    var updatedAt by WalkPathRatings.updatedAt
}