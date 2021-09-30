package dat257.gyro.shared.types

interface TransferType<T> {
    fun toJson(data: T): String
    fun fromJson(data: String): T
}