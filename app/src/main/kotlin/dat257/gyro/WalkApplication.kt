package dat257.gyro

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WalkApplication: Application() {
    fun getContext(): Context = this
}