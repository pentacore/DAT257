package dat257.gyro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import dat257.gyro.fragments.MapFragment
import dat257.gyro.fragments.TimerFragment
import dat257.gyro.services.LocationService

class MainActivity : AppCompatActivity() {
    lateinit var mapFragment : MapFragment
    lateinit var timerFragmentFragment : TimerFragment
    private var hasMapInitialized = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //Setting up timer in code instead
        timerFragmentFragment = TimerFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.timerFragment, timerFragmentFragment)
            commit()
        }
        /**
         * @author Jonathan
         */
        startService(
            Intent(this, LocationService().javaClass) // Fråga mig inte ens , läs på om intents, dem verkar coola osv
        )

        //Navigationbar fragments
        val helloWorldFragment = Fragment(R.layout.fragment_hello_world)
        mapFragment = MapFragment()
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
        if (!hasMapInitialized) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fcv_map, fragment)
                commit()
            }
            //supportFragmentManager.executePendingTransactions() //FragmentInfo can only be accessed after it has been initialized
        }
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fcv_main, Fragment()) //Replaces the main view with an empty fragment
                commit()
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcv_main, fragment)
            commit()
        }
    }
}