package dat257.gyro.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dat257.gyro.data.local.dao.SettingsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserSettings::class,
        UnitOfMeasure::class,
        VelocitySystem::class
    ],
    version = 1
)
abstract class RoomStorage : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao

    private class RoomStorageCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { dataBase ->
                scope.launch {
                    populateDatabase(dataBase.settingsDao())
                }
            }
        }

        suspend fun populateDatabase(settingsDao: SettingsDao) =
            settingsDao.purge()
                .also { fetchOrStandard(settingsDao) }

        /**
         * Implement fetch mechanics later
         */
        suspend fun fetchOrStandard(settingsDao: SettingsDao) = with(settingsDao) {
            insertSystemsOfMeasure("METRIC", "IMPERIAL")
            insertVelocitySystem("PACE","SPEED")
            insertUnitsOfMeasurement("M","FT","YD","KM","MI")
            insertSetting(
                UserSettings(
                    userName = "Kim Doe",
                    velocityMeasurement = "PACE",
                    systemOfMeasurement = "METRIC",
                    preferredLength = 5,
                    lengthUnit = "KILOMETER"
                )
            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: RoomStorage? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): RoomStorage {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    RoomStorage::class.java,
                    "local-storage"
                ).addCallback(RoomStorageCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
