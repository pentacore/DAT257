package dat257.gyro

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.internal.ContextUtils.getActivity
import org.osmdroid.api.IGeoPoint
import org.osmdroid.api.IMapController

import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapActivity : AppCompatActivity() {
    private val requestPermissionRequestCode = 1

    private lateinit var coordinateModel: Coordinator
    //Map
    private lateinit var map: MapView
    private lateinit var controller: IMapController

    //Coordinates
    private lateinit var coordinateView: TextView
    private lateinit var mLocationManager: LocationManager
    private var locationRefreshDistance: Float = 1.01f // exempel vet inte enhet
    private var locationRefreshTime: Long = 1
    private lateinit var userMarker: Marker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // load/initialize the osmdroid configuration
        // This won't work unless you have imported this: org.osmdroid.config.Configuration.*
        getInstance().load(this, this.getPreferences(0))
        // setting this before the layout is inflated is a good idea
        // it 'should' ensure that the map has a writable location for the map cache, even without permissions
        // if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        // see also StorageUtils
        // note, the load method also sets the HTTP User Agent to your application's package name; abusing the osm
        // tile servers will get you banned based on this string.

        // inflate and create the map
        setContentView(R.layout.activity_map)

        map = findViewById(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)

        val mLocationListener =
            LocationListener { setCordText(it.longitude, it.latitude, it.altitude) }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

        mLocationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, locationRefreshTime,
            locationRefreshDistance, mLocationListener
        )

        // set zoom and lat/lng for initial view
        controller = map.controller
        controller.setZoom(15.0)
        val gothenburg: IGeoPoint = GeoPoint(57.708870, 11.974560)
        controller.setCenter(gothenburg)

        userMarker = Marker(map)
        userMarker.position = GeoPoint(57.708870, 11.974560)
        map.overlays.add(userMarker)


    }

    override fun onResume() {
        super.onResume()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume() //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause()  //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsToRequest = ArrayList<String>()
        var i = 0
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i])
            i++
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                requestPermissionRequestCode
            )
        }
    }

    private fun setCordText(longitude: Double, latitude: Double, altitude: Double): Int =
        Log.i("Coordinates: ", "long:$longitude, lat:$latitude, alt:$altitude")
            .also { userMarker.position = GeoPoint(latitude, longitude) }
            .also { controller.setCenter(userMarker.position) }
}
