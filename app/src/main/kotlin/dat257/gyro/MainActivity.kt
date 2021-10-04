package dat257.gyro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import org.osmdroid.views.MapView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val helloWorldFragment = Fragment(R.layout.fragment_hello_world)
        val helloMapFragment = MapFragment()
        val helloSettingsFragment = Fragment(R.layout.fragment_hello_settings)
        val settingsFragment = SettingsFragment()

        setCurrentFragment(helloWorldFragment)

        findViewById<NavigationBarView>(R.id.bottomNavigation).setOnItemSelectedListener {
            when(it.itemId) {
                R.id.item_home -> setCurrentFragment(helloWorldFragment)
                R.id.item_map -> setCurrentFragment(helloMapFragment)
                R.id.item_settings -> setCurrentFragment(settingsFragment, true)
            }
            true
        }


    }

    private fun setCurrentFragment(fragment: Fragment, hideTimer:Boolean = false) {

        var isSame = false
        if(supportFragmentManager.findFragmentById(R.id.fcv_main) == fragment)
        {  isSame = true }


        if(!isSame) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fcv_main, fragment)
                commit()
            }

            var isHidden = true
            val x = supportFragmentManager.findFragmentById(R.id.Timerfragment)
            if (x != null){isHidden = x.isHidden}


            if(hideTimer && !isHidden){
                if (x != null) {
                    supportFragmentManager.beginTransaction().apply { hide(x)
                    commit()}
                }
            }
            else if(isHidden && !isSame){
            if (x != null) {
                supportFragmentManager.beginTransaction().apply{ show(x)
                    commit() }
            }
            }
        }
    }
}