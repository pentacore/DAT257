package dat257.gyro.shared.dataTypes

import dat257.gyro.shared.enums.ApiErrorCode
import dat257.gyro.shared.enums.RequestMethod
import dat257.gyro.shared.exceptions.RequiredDataMissingException
import dat257.gyro.shared.notNullAndGTZero
import dat257.gyro.shared.notNullOrEmpty
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
    var devices: Array<Device>? = null,
    var friends: Array<Friend>? = null,
    var blocks: Array<Block>? = null,
    var messages: Array<Message>? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var fetchDevices: Boolean = false,
    var fetchFriends: Boolean = false,
    var fetchBlocks: Boolean = false,
    var fetchMessages: Boolean = false,
    var fetchPreferences: Boolean = false
) : TransferType() {

    override fun validateForRequest(method: RequestMethod): Boolean {
        val valid = when (method) {
            RequestMethod.GET -> Int.notNullAndGTZero(id)
            RequestMethod.STORE -> {
                String.notNullOrEmpty(uuid) && validateArray(devices, method)
            }
            RequestMethod.UPDATE -> {
                Int.notNullAndGTZero(id) &&
                        String.notNullOrEmpty(uuid)
            }
            RequestMethod.DELETE -> Int.notNullAndGTZero(id)
        }

        if (!valid) {
            throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from Profile object for a $method request"))
        }

        return valid
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Profile

        if (id != other.id) return false
        if (uuid != other.uuid) return false
        if (name != other.name) return false
        if (avatar != other.avatar) return false
        if (banned != other.banned) return false
        if (devices != null) {
            if (other.devices == null) return false
            if (!devices.contentEquals(other.devices)) return false
        } else if (other.devices != null) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (uuid?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (avatar?.hashCode() ?: 0)
        result = 31 * result + (banned?.hashCode() ?: 0)
        result = 31 * result + (devices?.contentHashCode() ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        return result
    }

    @Serializable
    data class Device(
        var id: Int? = null,
        var hwid: String? = null,
        var profileId: Int? = null,
        var model: String? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null,
    ) : TransferType() {
        override fun validateForRequest(method: RequestMethod): Boolean {
            val valid = when (method) {
                RequestMethod.GET -> Int.notNullAndGTZero(id)
                RequestMethod.STORE -> {
                    String.notNullOrEmpty(hwid)
                }
                RequestMethod.UPDATE -> {
                    Int.notNullAndGTZero(id) && String.notNullOrEmpty(hwid)
                }
                RequestMethod.DELETE -> Int.notNullAndGTZero(id)
            }

            if (!valid) {
                throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from Device object for a $method request"))
            }

            return valid
        }
    }

    @Serializable
    data class Friend(
        var id: Int? = null,
        var user: Profile? = null,
        var friend: Profile? = null,
        var pending: Boolean? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null,
    ) : TransferType() {
        override fun validateForRequest(method: RequestMethod): Boolean {
            val valid = when (method) {
                RequestMethod.GET -> Int.notNullAndGTZero(id) || Int.notNullAndGTZero(user?.id)
                RequestMethod.STORE -> {
                    Int.notNullAndGTZero(id)
                }
                RequestMethod.UPDATE -> {
                    Int.notNullAndGTZero(id) && pending != null
                }
                RequestMethod.DELETE -> Int.notNullAndGTZero(id)
            }

            if (!valid) {
                throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from Friend object for a $method request"))
            }

            return valid
        }
    }

    @Serializable
    data class Block(
        var id: Int? = null,
        var user: Profile? = null,
        var blockedProfile: Profile? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null,
    ) : TransferType() {
        override fun validateForRequest(method: RequestMethod): Boolean {
            val valid = when (method) {
                RequestMethod.GET -> Int.notNullAndGTZero(id) || Int.notNullAndGTZero(user?.id)
                RequestMethod.STORE -> {
                    Int.notNullAndGTZero(blockedProfile?.id)
                }
                RequestMethod.UPDATE -> {
                    return false
                }
                RequestMethod.DELETE -> Int.notNullAndGTZero(id)
            }

            if (!valid) {
                throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from Blocked object for a $method request"))
            }

            return valid
        }
    }

    @Serializable
    data class Message(
        var id: Int? = null,
        var recipient: Profile? = null,
        var sender: Profile? = null,
        var title: String? = null,
        var message: String? = null,
        var ipAddress: String? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null,
        var fetchMessageContent: Boolean = false
    ) : TransferType() {
        override fun validateForRequest(method: RequestMethod): Boolean {
            val valid = when (method) {
                RequestMethod.GET -> Int.notNullAndGTZero(id) || Int.notNullAndGTZero(recipient?.id) || Int.notNullAndGTZero(sender?.id)
                RequestMethod.STORE -> {
                    Int.notNullAndGTZero(recipient?.id) &&
                            Int.notNullAndGTZero(sender?.id) &&
                            String.notNullOrEmpty(title) &&
                            String.notNullOrEmpty(message)
                }
                RequestMethod.UPDATE -> {
                    Int.notNullAndGTZero(id) &&
                            String.notNullOrEmpty(title) &&
                            String.notNullOrEmpty(message)
                }
                RequestMethod.DELETE -> Int.notNullAndGTZero(id)
            }

            if (!valid) {
                throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from Blocked object for a $method request"))
            }

            return valid
        }
    }

    @Serializable
    data class Preferences(
        var profileId: Int? = null,
        var showOnMap:Boolean?=null,
        var allowFriendRequests:Boolean?=null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
    ) : TransferType() {
        override fun validateForRequest(method: RequestMethod): Boolean {
            val valid = when (method) {
                RequestMethod.GET -> Int.notNullAndGTZero(profileId)
                RequestMethod.STORE -> {
                    showOnMap != null && allowFriendRequests != null
                }
                RequestMethod.UPDATE -> {
                    Int.notNullAndGTZero(profileId) &&
                            showOnMap != null &&
                            allowFriendRequests != null
                }
                RequestMethod.DELETE -> return false
            }

            if (!valid) {
                throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "Missing required fields from Blocked object for a $method request"))
            }

            return valid
        }
    }
}