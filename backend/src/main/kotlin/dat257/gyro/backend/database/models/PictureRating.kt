package dat257.gyro.backend.database.models

import dat257.gyro.backend.database.tables.PictureRatings
import dat257.gyro.backend.database.types.Ratings
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PictureRating(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PictureRating>(PictureRatings)

    var profile by Profile referencedOn PictureRatings.profile
    var picture by Picture referencedOn PictureRatings.picture
    var rating by PictureRatings.rating.transform({it.value},{ Ratings.valueOf(it.toString())})
    var comment by PictureRatings.comment
    var createdAt by PictureRatings.createdAt
    var updatedAt by PictureRatings.updatedAt
}