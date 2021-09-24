package dat257.gyro.data

import java.time.LocalDateTime

data class CoordinateRecord(val entry: List<Pair<Coordinate, LocalDateTime>>) {
    fun append(record: CoordinateRecord): Coordinate = TODO()
    fun whenAt(place: Coordinate): LocalDateTime = TODO()
    fun whereDuring(time: LocalDateTime): Coordinate = TODO()
}