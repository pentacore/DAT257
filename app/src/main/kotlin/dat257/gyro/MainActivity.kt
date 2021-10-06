package dat257.gyro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private var mapLoaded = false
    lateinit var mapFragmentInfo: MapFragmentInfo
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val helloWorldFragment = Fragment(R.layout.fragment_hello_world)
        val mapFragment = MapFragment()
        val helloSettingsFragment = Fragment(R.layout.fragment_hello_settings)

        setCurrentFragment(helloWorldFragment)

        findViewById<NavigationBarView>(R.id.bottomNavigation).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> setCurrentFragment(helloWorldFragment)
                R.id.item_map -> activateMapFragment(mapFragment)
                R.id.item_settings -> setCurrentFragment(helloSettingsFragment)
            }
            true
        }

    }

    private fun activateMapFragment(fragment: MapFragment) {
        if (!mapLoaded) {
            //Perform boolcheck here on data model to not restart this every time
            mapLoaded = true
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fcv_map, fragment)
                commit()
            }
            supportFragmentManager.executePendingTransactions() //FragmentInfo can only be accessed after it has been initialized
            mapFragmentInfo = fragment.mapFragmentInfo
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fcv_main, Fragment()) //Replaces the main view with an empty fragment
                commit()
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcv_main, fragment)
            commit()
        }
    }
}