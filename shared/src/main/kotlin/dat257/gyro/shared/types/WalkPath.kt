package dat257.gyro.shared.types

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class WalkPath(
    var name: String? = null,
    var description: String? = null,
    var public: Boolean? = null,
    var classification: Classification? = null,
    var nodes: Array<Node>? = null,
    var ratings: Array<Rating>? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) : TransferType<WalkPath> {
    override fun toJson(data: WalkPath): String {
        TODO("Not yet implemented")
    }

    override fun fromJson(data: String): WalkPath {
        TODO("Not yet implemented")
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
        var pathType: PathType? = null,
        var areaType: AreaType? = null,
        var handicapFriendly: Boolean? = null,
        var strollerFriendly: Boolean? = null,
        var lighted: Boolean? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
    ) : TransferType<Classification> {
        override fun toJson(data: Classification): String {
            TODO("Not yet implemented")
        }

        override fun fromJson(data: String): Classification {
            TODO("Not yet implemented")
        }
    }

    @Serializable
    data class Node(
        val longitude: Double? = null,
        var latitude: Double? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
    ) : TransferType<Node> {
        override fun toJson(data: Node): String {
            return ""
        }

        override fun fromJson(data: String): Node {
            TODO("Not yet implemented")
        }
    }

    @Serializable
    data class Rating(
        var profileName: String? = null,
        var rating: dat257.gyro.shared.types.Rating? = null,
        var comment: String? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
    ) : TransferType<Rating> {
        override fun toJson(data: Rating): String {
            TODO("Not yet implemented")
        }

        override fun fromJson(data: String): Rating {
            TODO("Not yet implemented")
        }
    }
}
