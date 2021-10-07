package dat257.gyro.shared.dataTypes

import dat257.gyro.shared.Latitude
import dat257.gyro.shared.Longitude
import dat257.gyro.shared.enums.ApiErrorCode
import dat257.gyro.shared.enums.AreaType
import dat257.gyro.shared.enums.PathType
import dat257.gyro.shared.enums.RequestMethod
import dat257.gyro.shared.exceptions.RequiredDataMissingException
import dat257.gyro.shared.notNullAndGTZero
import dat257.gyro.shared.notNullOrEmpty
import dat257.gyro.shared.types.TransferType
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class WalkPath(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var public: Boolean? = null,
    var classification: Classification? = null,
    var nodes: Array<Node>? = null,
    var ratings: Array<Rating>? = null,
    var avgRating: Rating? = null,
    var owner: Profile? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var fetchClassification: Boolean = false,
    var fetchNodes: Boolean = false,
    var fetchRatings: Boolean = false,
    var fetchOwner: Boolean = true
) : TransferType() {
    override fun validateForRequest(method: RequestMethod): Boolean {
        val valid = when (method) {
            RequestMethod.GET -> return true
            RequestMethod.STORE -> {
                String.notNullOrEmpty(name) &&
                        public != null &&
                        notNullAndValid(classification, method) &&
                        validateArray(nodes, method)
            }
            RequestMethod.UPDATE -> {
                Int.notNullAndGTZero(id) &&
                        String.notNullOrEmpty(name) &&
                        notNullAndValid(classification, method) &&
                        validateArray(nodes, method)
            }
            RequestMethod.DELETE -> TODO()
        }

        if (!valid) {
            throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from WalkPath object for a $method request"))
        }

        return valid
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WalkPath

        if (name != other.name) return false
        if (description != other.description) return false
        if (public != other.public) return false
        if (classification != other.classification) return false
        if (!nodes.contentEquals(other.nodes)) return false
        if (!ratings.contentEquals(other.ratings)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + public.hashCode()
        result = 31 * result + classification.hashCode()
        result = 31 * result + nodes.contentHashCode()
        result = 31 * result + ratings.contentHashCode()
        return result
    }

    @Serializable
    data class Classification(
        var id: Int? = null,
        var walkPathId: Int? = null,
        var pathType: PathType? = null,
        var areaType: AreaType? = null,
        var handicapFriendly: Boolean? = null,
        var strollerFriendly: Boolean? = null,
        var lighted: Boolean? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
    ) : TransferType() {
        override fun validateForRequest(method: RequestMethod): Boolean {
            val valid = when (method) {
                RequestMethod.GET -> Int.notNullAndGTZero(id) || Int.notNullAndGTZero(walkPathId)
                RequestMethod.STORE -> TODO()
                RequestMethod.UPDATE -> TODO()
                RequestMethod.DELETE -> TODO()
            }

            if (!valid) {
                throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from Classification object for a $method request"))
            }

            return valid
        }
    }

    @Serializable
    data class Node(
        val id: Int? = null,
        val walkPathId: Int? = null,
        val longitude: Longitude? = null,
        var latitude: Latitude? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
    ) : TransferType() {
        override fun validateForRequest(method: RequestMethod): Boolean {
            val valid = when (method) {
                RequestMethod.GET -> Int.notNullAndGTZero(id) || Int.notNullAndGTZero(walkPathId)
                RequestMethod.STORE,RequestMethod.UPDATE -> {
                    longitude != null && latitude != null
                }
                RequestMethod.DELETE -> Int.notNullAndGTZero(id)
            }

            if (!valid) {
                throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from Node object for a $method request"))
            }

            return valid
        }
    }

    @Serializable
    data class Rating(
        val id: Int? = null,
        val ownerId: Int? = null,
        val walkPathId: Int? = null,
        var profile: Profile? = null,
        var rating: dat257.gyro.shared.enums.Rating? = null,
        var comment: String? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
    ) : TransferType() {
        override fun validateForRequest(method: RequestMethod): Boolean {
            val valid = when (method) {
                RequestMethod.GET -> Int.notNullAndGTZero(id) || Int.notNullAndGTZero(walkPathId) || Int.notNullAndGTZero(ownerId)
                RequestMethod.STORE,RequestMethod.UPDATE -> {
                    rating != null && comment != null
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
