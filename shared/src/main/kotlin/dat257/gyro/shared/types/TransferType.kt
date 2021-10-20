package dat257.gyro.shared.types

import dat257.gyro.shared.enums.RequestMethod
import kotlinx.serialization.Serializable
import org.http4k.core.Method

@Serializable
abstract class TransferType {
    abstract fun validateForRequest(method: RequestMethod): Boolean

    companion object {
        fun <T : TransferType> notNullAndValid(obj: T?, method: RequestMethod): Boolean {
            return obj != null && obj.validateForRequest(method)
        }

        fun <T : TransferType> validateArray(array: Array<T>?, method: RequestMethod): Boolean {
            if (array == null || array.isEmpty()) {
                return false;
            }
            for (obj in array) {
                if (!obj.validateForRequest(method)) {
                    return false
                }
            }
            return true
        }
    }
}