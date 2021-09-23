package dat257.gyro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.mapButton).setOnClickListener { startMapActivity() }
    }

    private fun startMapActivity() {
        startActivity(Intent(this, MapActivity::class.java))
    }
}