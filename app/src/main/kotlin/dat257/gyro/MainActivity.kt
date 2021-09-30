package dat257.gyro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.OneShotPreDrawListener.add


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //add<FirstFragment>(R.id.fragment)
        findViewById<Button>(R.id.mapButton).setOnClickListener { startMapActivity() }



    }

    private fun startMapActivity() {
        startActivity(Intent(this, MapActivity::class.java))
    }



}