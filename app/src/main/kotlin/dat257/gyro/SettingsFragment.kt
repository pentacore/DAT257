package dat257.gyro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.preference_screen, rootKey)

        val hostActivity: Context? = activity
        val preference = PreferenceManager.getDefaultSharedPreferences(hostActivity)


    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        return when (preference.key) {
            getString(R.string.displayName) -> {
                true
            }
            getString(R.string.Measurements) -> {
                true
            }
            getString(R.string.route_len) -> {
                true
            }
            else -> {
                super.onPreferenceTreeClick(preference)
            }
        }
    }
}
