package dat257.gyro.shared.dataTypes

import dat257.gyro.shared.Latitude
import dat257.gyro.shared.Longitude
import dat257.gyro.shared.enums.ApiErrorCode
import dat257.gyro.shared.enums.RequestMethod
import dat257.gyro.shared.exceptions.RequiredDataMissingException
import dat257.gyro.shared.notNullAndGTZero
import dat257.gyro.shared.notNullOrEmpty
import dat257.gyro.shared.types.TransferType
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Picture(
    var id: Int? = null,
    var profileId: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var base64: String? = null,
    var longitude: Longitude? = null,
    var latitude: Latitude? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var fetchRatings: Boolean = false,
    var fetchImageData: Boolean = false
) : TransferType() {
    override fun validateForRequest(method: RequestMethod): Boolean {
        val valid = when (method) {
            RequestMethod.GET -> return true
            RequestMethod.STORE -> {
                String.notNullOrEmpty(title) &&
                        String.notNullOrEmpty(base64) &&
                        longitude != null &&
                        latitude != null
            }
            RequestMethod.UPDATE -> {
                Int.notNullAndGTZero(id) &&
                        String.notNullOrEmpty(title) &&
                        String.notNullOrEmpty(base64) &&
                        longitude != null &&
                        latitude != null
            }
            RequestMethod.DELETE -> Int.notNullAndGTZero(id)
        }

        if (!valid) {
            throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from WalkPath object for a $method request"))
        }

        return valid
    }

    @Serializable
    data class Rating(
        val id: Int? = null,
        val ownerId: Int? = null,
        val pictureId: Int? = null,
        var profile: Profile? = null,
        var rating: dat257.gyro.shared.enums.Rating? = null,
        var comment: String? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
    ) : TransferType() {
        override fun validateForRequest(method: RequestMethod): Boolean {
            val valid = when (method) {
                RequestMethod.GET -> Int.notNullAndGTZero(id) || Int.notNullAndGTZero(pictureId) || Int.notNullAndGTZero(ownerId)
                RequestMethod.STORE -> {
                    rating != null && comment != null
                }
                RequestMethod.UPDATE -> {
                    Int.notNullAndGTZero(id) &&
                            rating != null &&
                            comment != null
                }
                RequestMethod.DELETE -> Int.notNullAndGTZero(id)
            }

            if (!valid) {
                throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from Rating object for a $method request"))
            }

            return valid
        }
    }
}