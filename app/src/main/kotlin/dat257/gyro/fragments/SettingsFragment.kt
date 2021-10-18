package dat257.gyro.fragments

import android.content.Context
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import dat257.gyro.R

//https://developer.android.com/reference/androidx/preference/PreferenceFragmentCompat
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
