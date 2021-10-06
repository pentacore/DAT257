package dat257.gyro.shared.exceptions

import dat257.gyro.shared.enums.ApiErrorCode
import dat257.gyro.shared.dataTypes.ApiError

abstract class ApiException(val apiError: ApiError):Exception() {
    abstract val errorCode:ApiErrorCode

    companion object {
        fun getException(apiError: ApiError):ApiException {
            return when (apiError.errorCode) {
                ApiErrorCode.AlreadyExists -> AlreadyExistsException(apiError)
                ApiErrorCode.TargetNotFound -> TargetNotFoundException(apiError)
                ApiErrorCode.MissingRequiredData -> RequiredDataMissingException(apiError)
                ApiErrorCode.UserNotFound -> UserNotFoundException(apiError)
            }
        }
    }
}