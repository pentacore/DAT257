package dat257.gyro.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import dat257.gyro.PermissionHandler
import dat257.gyro.patterns.publisherSubscriber.ChannelName
import dat257.gyro.patterns.publisherSubscriber.Message
import dat257.gyro.patterns.publisherSubscriber.Publisher

private const val REFRESH_TIME: Long = 10000 // exempel vet inte enhet

/**
 * @author Jonathan
 * källa för service: https://dzone.com/articles/how-to-create-a-background-service-in-android
 */
class LocationService : Service(), Publisher {
    override fun onBind(p0: Intent?): IBinder? = null

    //Coordinates
    private lateinit var locationClient: FusedLocationProviderClient
    /**
     * @author Jonathan
     * @author Erik
     */
    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        createChannel(ChannelName.Location)
        PermissionHandler.location(this)
        locationClient = LocationServices.getFusedLocationProviderClient(this)
        locationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    onLocationUpdate(location)
                }
            }
        val locationRequest = createLocationRequest()

        val locationCallback = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult){
                onLocationUpdate(locationResult.locations.last())
            }
        }
        locationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()!!
        )
    }

    /**
     * @author Erik
     */
    private fun createLocationRequest(): LocationRequest {
        return LocationRequest.create().apply {
            interval = REFRESH_TIME
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    /**
     * @author Jonathan
     */
    fun onLocationUpdate(location: Location) = with(Message(location)) {
        publish(ChannelName.Location, this)
    }.also {
        with(location){
            Log.i("Location","Updated:\n" +
                    " longitude: $longitude \n" +
                    " latitude: $latitude \n" +
                    " altitude $altitude \n")
        }
    }
}