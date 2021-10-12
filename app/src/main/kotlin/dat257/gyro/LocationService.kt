package dat257.gyro

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.IBinder
import android.util.Log
import dat257.gyro.patterns.publisherSubscriber.ChannelName
import dat257.gyro.patterns.publisherSubscriber.Message
import dat257.gyro.patterns.publisherSubscriber.Publisher

/**
 * @author Jonathan
 * källa för service: https://dzone.com/articles/how-to-create-a-background-service-in-android
 */
class LocationService: Service(), Publisher {
    override fun onBind(p0: Intent?): IBinder? = null
    //Coordinates
    private lateinit var manager: LocationManager
    private val refreshDistance: Float = 1.01f // exempel vet inte enhet
    private val refreshTime: Long = 1
    private lateinit var locationListener: LocationListener

    /**
     * @author Jonathan
     */
    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        createChannel(ChannelName.Location)
        manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = LocationListener { onLocationUpdate(it) }
        PermissionHandler.location(this)
        manager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, refreshTime,
            refreshDistance, locationListener
        )
    }

    /**
     * @author Jonathan
     */
    private fun onLocationUpdate(location: Location) = with(Message(location)){
        Log.d("LocationService","Location updated")
        publish(ChannelName.Location,this)
    }
}