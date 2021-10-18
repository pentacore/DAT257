package dat257.gyro.data.local.dao

import androidx.room.*
import dat257.gyro.data.local.UserSettings

@Dao
interface SettingsDao {
    @Query("SELECT * FROM UserSettings")
    suspend fun getSettings() : List<UserSettings>

    @Query("SELECT (:setting) FROM UserSettings")
    suspend fun gsSetting(setting: String): List<String>

    @Delete
    suspend fun removeSettings(settings: UserSettings)

    @Update
    suspend fun updateSetting(setting: UserSettings)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSetting(settings: UserSettings)

    @Query("DELETE FROM UserSettings")
    suspend fun purge()
}