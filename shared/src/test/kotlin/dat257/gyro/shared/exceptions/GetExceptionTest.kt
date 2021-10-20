package dat257.gyro.shared.exceptions

import dat257.gyro.shared.dataTypes.ApiError
import dat257.gyro.shared.enums.ApiErrorCode
import org.junit.Ignore
import org.junit.Test

@Ignore
class GetExceptionTest {
    @Test
    fun testParseAllKnownErrorCodes() {
        for(errorCode in ApiErrorCode.values()) {
            val error = ApiError(errorCode, "Exception Testing ${errorCode.code}")
            assert(ApiException.getException(error).errorCode == errorCode)
        }
    }
}