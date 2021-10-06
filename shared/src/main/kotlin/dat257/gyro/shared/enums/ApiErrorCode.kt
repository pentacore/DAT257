package dat257.gyro.shared.enums

enum class ApiErrorCode(val code:Int) {
    AlreadyExists(40),
    TargetNotFound(41),
    MissingRequiredData(42)
}