package dat257.gyro.shared.types

import dat257.gyro.shared.ApiHelper
import dat257.gyro.shared.dataTypes.ApiError
import dat257.gyro.shared.enums.RequestMethod
import dat257.gyro.shared.exceptions.ApiException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.client.JavaHttpClient
import org.http4k.core.*
import java.util.*

abstract class RequestType<DataType : TransferType>(private val profileUUID: UUID, private val apiToken:String) {
    protected abstract val endpointUrl: String
    protected abstract val data: DataType
    protected val client: HttpHandler = JavaHttpClient()

    protected fun send(request:Request): Response {
        request.header(ApiHelper.API_TOKEN_HEADER, apiToken)
        request.header(ApiHelper.PROFILE_UUID_HEADER, profileUUID.toString())
        val res = client(request)

        if (res.status != Status.OK) {
            try {
                val error = Json.decodeFromString<ApiError>(res.bodyString())
                throw ApiException.getException(error)
            } catch (_: IllegalStateException) {
                //Was not an error
            }
            throw Exception("Something went wrong")
        }

        return res
    }

    protected fun send(data: TransferType, endpointUrl: String, method: RequestMethod): Response {
        return send(data,endpointUrl,method.to4KMethod())
    }

    protected fun send(data: TransferType, endpointUrl: String, method: Method): Response {
        val request = Request(method, endpointUrl)
        this.data.validateForRequest(RequestMethod.getFrom4KMethod(method))
        request.body(Json.encodeToString(data))
        return send(request)
    }

    abstract fun store(): Pair<Status, DataType>
    abstract fun get(): Pair<Status, DataType>
    abstract fun delete(): Status
    abstract fun update(): Pair<Status, DataType>
}