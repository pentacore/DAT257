package dat257.gyro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import dat257.gyro.fragments.MapFragment
import dat257.gyro.model.ChannelCreator
import dat257.gyro.services.LocationService

class MainActivity : AppCompatActivity() {

    private lateinit var mapFragment: MapFragment
    private var hasMapInitialized = false
    override fun onCreate(savedInstanceState: Bundle?) {

        //Navigationbar fragments
        val helloWorldFragment = Fragment(R.layout.fragment_hello_world)
        mapFragment = MapFragment()
        val helloSettingsFragment = SettingsFragment()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        ChannelCreator()
        //Setting up timer in code instead
        /**
         * @author Jonathan
         */
        startService(
            Intent(
                this,
                LocationService().javaClass
            ) // Fråga mig inte ens , läs på om intents, dem verkar coola osv
        )


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
        //it is not nice but it gets the job done
        if (fragment != mapFragment) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fcv_main, fragment).remove(mapFragment)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fcv_main, fragment)
                commit()
            }

        }
    }
}