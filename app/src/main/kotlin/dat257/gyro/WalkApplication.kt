package dat257.gyro

import android.app.Application
import androidx.room.RoomDatabase
import dagger.hilt.android.HiltAndroidApp
import dat257.gyro.data.local.RoomStorage
import dat257.gyro.data.local.RoomStorage.Companion.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class WalkApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { RoomStorage.getDatabase(this,applicationScope) }
}