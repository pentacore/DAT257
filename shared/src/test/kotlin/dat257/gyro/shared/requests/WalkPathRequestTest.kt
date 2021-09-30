package dat257.gyro.shared.requests

import dat257.gyro.shared.dataTypes.WalkPath
import dat257.gyro.shared.requests.WalkPathRequest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.hamcrest.CoreMatchers.any
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class WalkPathRequestTest {
    @SpyK(recordPrivateCalls = true)
    var request: WalkPathRequest = WalkPathRequest(WalkPath(), UUID.randomUUID(), "")

    @Before
    fun init() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testStoreWalkPathRequest() {
        val response: Response = Response(Status.OK).body(Json.encodeToString(WalkPath()))
        every { request["send"](any<Request>()) } returns response
        val res = request.store()

        assertEquals(Status.OK,res.first)
    }

    @Test
    fun testGetWalkPathRequest() {
        val response: Response = Response(Status.OK).body(Json.encodeToString(WalkPath()))
        every { request["send"](any<Request>()) } returns response
        val res = request.get()

        assertEquals(Status.OK,res.first)
    }

    @Test
    fun testDeleteWalkPathRequest() {
        val response = Response(Status.OK)
        every { request["send"](any<Request>()) } returns response

        assertEquals(Status.OK, request.delete())
    }

    @Test
    fun testUpdateWalkPathRequest() {
        val response: Response = Response(Status.OK).body(Json.encodeToString(WalkPath()))
        every { request["send"](any<Request>()) } returns response
        val res = request.update()

        assertEquals(Status.OK,res.first)
    }
}