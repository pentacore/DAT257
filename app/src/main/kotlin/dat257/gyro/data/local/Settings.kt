package dat257.gyro.data.local

import androidx.room.*

/**
 * Jonathan
 * Jag har inte skapat några relationer mellan tables,
 * det får komma i framtiden,
 * vill mest ha persistence just nu
 */
const val velocity_measurement = "velocity_measurement"
const val system_of_measurement = "system_of_measurement"
const val units_of_measurement = "units_of_measurement"
@Entity(foreignKeys = [
    ForeignKey(
        entity = SystemOfMeasurement::class,
        childColumns = arrayOf(system_of_measurement),
        parentColumns = arrayOf("name")
    ),
    ForeignKey(
        entity = UnitOfMeasure::class,
        childColumns = arrayOf(units_of_measurement),
        parentColumns = arrayOf("name")
    ),
    ForeignKey(
        entity = VelocitySystem::class,
        childColumns = arrayOf(velocity_measurement),
        parentColumns = arrayOf("name")
    )],
)
data class UserSettings(
    @PrimaryKey @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = velocity_measurement)
    val velocityMeasurement: String,
    @ColumnInfo(name = system_of_measurement)
    val systemOfMeasurement: String,
    val preferredLength: Int,
    @ColumnInfo(name = units_of_measurement)
    val lengthUnit: String
)

@Entity(indices = [Index(value = ["name"], unique = true)])
data class SystemOfMeasurement(@PrimaryKey val name: String)
@Entity(indices = [Index(value = ["name"], unique = true)])
data class UnitOfMeasure(@PrimaryKey val name: String)
@Entity(indices = [Index(value = ["name"], unique = true)])
data class VelocitySystem(@PrimaryKey val name: String)
