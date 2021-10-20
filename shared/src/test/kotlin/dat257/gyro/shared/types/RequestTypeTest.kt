package dat257.gyro.shared.types

import dat257.gyro.shared.dataTypes.ApiError
import dat257.gyro.shared.dataTypes.WalkPath
import dat257.gyro.shared.enums.ApiErrorCode
import dat257.gyro.shared.exceptions.ApiException
import dat257.gyro.shared.requests.WalkPathRequest
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.client.JavaHttpClient
import org.http4k.core.*
import org.junit.Ignore
import org.junit.Test
import java.util.*

@Ignore
class RequestTypeTest {
    @SpyK(recordPrivateCalls = true)
    var request: WalkPathRequest = WalkPathRequest(WalkPath(), UUID.randomUUID(), "")

    @Test
    fun testExceptionParsing() {
        val client = mockk<JavaHttpClient>()
        val response: Response = Response(Status.OK).body(Json.encodeToString(ApiError(ApiErrorCode.AlreadyExists, "Nah you cant do that fam")))
        every { request.client(any()) } returns response
        request.get()
    }
}