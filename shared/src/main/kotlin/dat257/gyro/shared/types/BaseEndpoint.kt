package dat257.gyro.shared.types

import dat257.gyro.shared.StatusCode

interface BaseEndpoint<DataType:TransferType<DataType>> {
    val endpointUrl: String
    val data: DataType

    abstract fun store(): StatusCode
    abstract fun get(): Pair<StatusCode, DataType>
    abstract fun delete(): StatusCode
}