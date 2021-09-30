package dat257.gyro.shared.requests

import dat257.gyro.shared.ApiHelper
import dat257.gyro.shared.dataTypes.WalkPath
import dat257.gyro.shared.types.RequestType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import java.util.*

class WalkPathRequest(override val data: WalkPath, profileUUID: UUID, apiToken: String) : RequestType<WalkPath>(profileUUID, apiToken) {
    override val endpointUrl = ApiHelper.getApiUrl("/api/walkpath")

    override fun store(): Pair<Status, WalkPath> {
        val request = Request(Method.PUT, endpointUrl)
        request.body(Json.encodeToString(data))
        val res = send(request)
        return res.status to Json.decodeFromString<WalkPath>(res.bodyString())
    }

    override fun get(): Pair<Status, WalkPath> {
        val request = Request(Method.GET, endpointUrl)
        request.body(Json.encodeToString(data))
        val res = send(request)
        return res.status to Json.decodeFromString<WalkPath>(res.bodyString())
    }

    override fun delete(): Status {
        val request = Request(Method.DELETE, endpointUrl)
        request.body(Json.encodeToString(data))
        val res = send(request)
        return res.status
    }

    override fun update(): Pair<Status, WalkPath> {
        val request = Request(Method.PATCH, endpointUrl)
        request.body(Json.encodeToString(data))
        val res = send(request)
        return res.status to Json.decodeFromString<WalkPath>(res.bodyString())
    }
}