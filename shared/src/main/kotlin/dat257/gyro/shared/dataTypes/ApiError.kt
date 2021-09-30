package dat257.gyro.shared.dataTypes

import dat257.gyro.shared.enums.ApiErrorCode
import dat257.gyro.shared.types.TransferType
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class ApiError(val errorCode: ApiErrorCode, val message:String) : TransferType() {
}