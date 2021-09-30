package dat257.gyro.shared.dataTypes

import dat257.gyro.shared.types.TransferType
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    var id: Int? = null,
    var uuid: String? = null,
    var name: String? = null,
    var avatar: String? = null,
    var banned: Boolean? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) : TransferType() {
}