package dat257.gyro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val helloWorldFragment = Fragment(R.layout.fragment_hello_world)
        val helloMapFragment = MapFragment()
        val helloSettingsFragment = Fragment(R.layout.fragment_hello_settings)

        setCurrentFragment(helloWorldFragment)

        findViewById<NavigationBarView>(R.id.bottomNavigation).setOnItemSelectedListener {
            when(it.itemId) {
                R.id.item_home -> setCurrentFragment(helloWorldFragment)
                R.id.item_map -> setCurrentFragment(helloMapFragment)
                R.id.item_settings -> setCurrentFragment(helloSettingsFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcv_main, fragment)
            commit()
        }
    }

}