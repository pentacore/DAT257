package dat257.gyro.shared.exceptions

import dat257.gyro.shared.dataTypes.ApiError
import dat257.gyro.shared.enums.ApiErrorCode

class TargetNotFoundException(apiError: ApiError):ApiException(apiError) {
    override val errorCode = ApiErrorCode.TargetNotFound
}