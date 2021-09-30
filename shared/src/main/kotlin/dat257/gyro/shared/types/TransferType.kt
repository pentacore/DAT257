package dat257.gyro.shared.types

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

abstract class TransferType<T> {
    fun toJson(): String {
        return Json.encodeToString(this)
    }
    abstract fun fromJson(data: String):T
}