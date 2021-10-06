package dat257.gyro.api

import dat257.gyro.BaseTest
import org.http4k.core.Method
import org.junit.Test
import kotlin.test.assertEquals

class WalkPathMethodTest: BaseTest() {
    @Test
    fun get() {
        println("Running WalkPathMethod:Get Test")
        val response = app(org.http4k.core.Request(Method.GET, "/api/walkpath"))
        assertEquals("ping", response.bodyString())
    }
}