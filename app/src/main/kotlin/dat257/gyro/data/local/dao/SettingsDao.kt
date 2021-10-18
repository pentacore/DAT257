package dat257.gyro.data.local.dao

import androidx.room.*
import dat257.gyro.data.local.SystemOfMeasurement
import dat257.gyro.data.local.UnitOfMeasure
import dat257.gyro.data.local.UserSettings
import dat257.gyro.data.local.VelocitySystem

@Dao
interface SettingsDao {
    @Query("SELECT * FROM UserSettings")
    suspend fun getSettings(): List<UserSettings>

    @Query("SELECT (:setting) FROM UserSettings")
    suspend fun getSetting(setting: String): List<String>

    @Update
    suspend fun updateSetting(setting: UserSettings)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSetting(settings: UserSettings)

    @Query("DELETE FROM UserSettings")
    suspend fun purge()

    suspend fun insertSystemsOfMeasure(vararg s: String) =
        s.forEach { iVel(VelocitySystem(it)) }

    suspend fun insertVelocitySystem(vararg s: String) =
        s.forEach { iSysOfMeasure(SystemOfMeasurement(it)) }

    suspend fun insertUnitsOfMeasurement(vararg s: String) =
        s.forEach { iUnit(UnitOfMeasure(it)) }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun iSysOfMeasure(s: SystemOfMeasurement)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun iVel(v: VelocitySystem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun iUnit(u: UnitOfMeasure)
}