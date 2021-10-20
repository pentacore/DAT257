package dat257.gyro.api

import dat257.gyro.BaseTest
import dat257.gyro.shared.ApiHelper.Companion.API_TOKEN_HEADER
import dat257.gyro.shared.ApiHelper.Companion.PROFILE_UUID_HEADER
import dat257.gyro.shared.dataTypes.WalkPath
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class WalkPathMethodTest : BaseTest() {
    @Before
    fun init() {
        createUser()
        createTestPath()
        createSecondaryUsers()
    }

    @Test
    fun getAllAvailable() {
        println("Running WalkPathMethod:Get Test")
        val model = WalkPath();
        val request: Request = org.http4k.core.Request(Method.GET, "/api/walkpath")
            .header(API_TOKEN_HEADER, profile?.uuid.toString())
            .header(PROFILE_UUID_HEADER, profile?.uuid.toString())
            .body(Json.encodeToString(model))
        val response = app(request)
        assertEquals(Status.OK, response.status)
        val obj = Json.decodeFromString<HashMap<String, Array<WalkPath>>>(response.bodyString())
        assertEquals(DEFAULT_USER_PATH_NUM, obj["user"]?.size)
        assertEquals(DEFAULT_SECONDARY_USERS_NUM * DEFAULT_SECONDARY_USERS_PUBLIC_PATH_NUM, obj["public"]?.size)
    }
}