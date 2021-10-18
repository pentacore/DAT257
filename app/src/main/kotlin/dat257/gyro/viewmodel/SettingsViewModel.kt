package dat257.gyro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dat257.gyro.data.Repository
import javax.inject.Inject


class SettingsViewModel @Inject constructor(
    repository: Repository,
    app: Application
): AndroidViewModel(app) {



}