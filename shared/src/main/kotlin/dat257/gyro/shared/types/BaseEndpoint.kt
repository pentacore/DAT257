package dat257.gyro.shared.types

import dat257.gyro.shared.StatusCode
import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler

abstract class BaseEndpoint<DataType : TransferType<DataType>> {
    protected abstract val endpointUrl: String
    protected abstract val data: DataType
    protected val client: HttpHandler = JavaHttpClient()

    abstract fun store(): StatusCode
    abstract fun get(): Pair<StatusCode, DataType>
    abstract fun delete(): StatusCode
}