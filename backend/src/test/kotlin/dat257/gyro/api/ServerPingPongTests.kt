package dat257.gyro.api

import dat257.gyro.BaseTest
import org.http4k.core.Method
import org.junit.Test
import kotlin.test.assertEquals

class ServerPingPongTests: BaseTest() {

    @Test
    fun testServerPing() {
        val response = app(org.http4k.core.Request(Method.GET, "/ping"))
        assertEquals("pong", response.bodyString())
    }

    @Test
    fun testServerPong() {
        val response = app(org.http4k.core.Request(Method.GET, "/pong"))
        assertEquals("ping", response.bodyString())
    }
}