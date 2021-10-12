package dat257.gyro

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.Task
import dat257.gyro.patterns.publisherSubscriber.ChannelName
import dat257.gyro.patterns.publisherSubscriber.Message
import dat257.gyro.patterns.publisherSubscriber.Publisher

/**
 * @author Jonathan
 * källa för service: https://dzone.com/articles/how-to-create-a-background-service-in-android
 */
class LocationService : Service(), Publisher {
    override fun onBind(p0: Intent?): IBinder? = null

    //Coordinates
    private lateinit var locationClient: FusedLocationProviderClient
    private val refreshDistance: Float = 1.01f // exempel vet inte enhet
    private val refreshTime: Long = 1
    private lateinit var locationListener: LocationListener

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
        //locationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
        /*
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())


        task.addOnSuccessListener { locationSettingsResponds ->
*/
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
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    /**
     * @author Jonathan
     */
    public fun onLocationUpdate(location: Location) = with(Message(location)) {
        Log.d("LocationService", "Location updated")
        publish(ChannelName.Location, this)
    }
}