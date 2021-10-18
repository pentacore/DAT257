package dat257.gyro.fragments

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import dat257.gyro.R

//https://developer.android.com/reference/androidx/preference/PreferenceFragmentCompat
//https://stackoverflow.com/questions/13596250/how-to-listen-for-preference-changes-within-a-preferencefragment
class SettingsFragment : PreferenceFragmentCompat() , OnSharedPreferenceChangeListener{

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.preference_screen, rootKey)
        val hostActivity: Context? = activity


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

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        TODO("Not yet implemented")
    }
}
