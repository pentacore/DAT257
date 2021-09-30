package dat257.gyro.shared.types

import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Status

abstract class EndpointType<DataType : TransferType<DataType>> {
    protected abstract val endpointUrl: String
    protected abstract val data: DataType
    protected val client: HttpHandler = JavaHttpClient()

    abstract fun store(): Status
    abstract fun get(): Pair<Status, DataType>
    abstract fun delete(): Status
}