package dat257.gyro.data.local

import androidx.room.*
import dat257.gyro.model.VelocityMeasurement

/**
 * Jonathan
 * Jag har inte skapat några relationer mellan tables,
 * det får komma i framtiden,
 * vill mest ha persistence just nu
 */
@Entity
data class UserSettings(
    @PrimaryKey
    val userName: String,
    @ColumnInfo(name = "velocity_measurement")
    val velocityMeasurement: String,
    @ColumnInfo(name = "system_of_measurement")
    val systemOfMeasurement: String,
    @ColumnInfo(name = "preferred_length")
    val preferredLength: Int,
    @ColumnInfo(name = "length_unit")
    val lengthUnit: String
)

@Entity(indices = [Index(value = ["name"], unique = true)])
data class SystemOfMeasurement(
    @PrimaryKey val name: String
)

@Entity(indices = [Index(value = ["name"], unique = true)])
data class UnitOfMeasure(
    @PrimaryKey val name: String
)

@Entity(indices = [Index(value = ["name"], unique = true)])
data class VelocitySystem(
    @PrimaryKey val name: String
)

val unitsOfMeasurement = listOf<String>(
    "METER",
    "KILOMETER",
    "MILE",
    "FEET"
)

val systemsOfMeasurement = listOf<String>(
    "METRIC",
    "IMPERIAL"
)