package dat257.gyro

import android.content.BroadcastReceiver
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import android.content.Intent

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.preference_screen, rootKey)
    }
}
