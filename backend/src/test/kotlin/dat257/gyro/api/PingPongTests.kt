package dat257.gyro.api

import dat257.gyro.BaseTest
import org.http4k.core.Method
import org.junit.Test
import kotlin.test.assertEquals

class PingPongTests: BaseTest() {

    @Test
    fun testServerPing() {
        println("Running Ping Test")
        val response = app(org.http4k.core.Request(Method.GET, "/ping"))
        assertEquals("pong", response.bodyString())
    }

    @Test
    fun testServerPong() {
        println("Running Pong Test")
        val response = app(org.http4k.core.Request(Method.GET, "/pong"))
        assertEquals("ping", response.bodyString())
    }

}