package dat257.gyro.fragments

import android.icu.text.SimpleDateFormat
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi

import androidx.fragment.app.Fragment
import dat257.gyro.IllegalPayloadException
import dat257.gyro.R
import dat257.gyro.patterns.publisherSubscriber.ChannelName
import dat257.gyro.patterns.publisherSubscriber.Message
import dat257.gyro.patterns.publisherSubscriber.Subscriber
import dat257.gyro.services.RouteData
import org.osmdroid.api.IMapController

import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import java.util.*

class MapFragment : Fragment(), Subscriber {

    //Map
    private lateinit var map: MapView
    private lateinit var controller: IMapController
    private var routeLineDrawn: Polyline = Polyline()
    private var isFollowModeActive = false

    //Lock button
    private lateinit var lockButton: Button
    private var bearing: Float = 0F

    //Conceptual time
    private lateinit var simpleDateFormat: SimpleDateFormat

    //Coordinates
    private lateinit var overlay: MyLocationNewOverlay
    private lateinit var location: Location

    private var recordedRoute = Route(mutableListOf())

    /**
     * @author Felix
     * @author Jonathan
     * @author Erik
     */
    // Detta borde vi oroa oss över senare
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val activityContext = activity
        // load/initialize the osmdroid configuration
        // This won't work unless you have imported this: org.osmdroid.config.Configuration.*
        if (activityContext != null) {
            getInstance().load(activityContext, activityContext.getSharedPreferences(null, 0))
        }
        // setting this before the layout is inflated is a good idea
        // it 'should' ensure that the map has a writable location for the map cache, even without permissions
        // if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        // see also StorageUtils
        // note, the load method also sets the HTTP User Agent to your application's package name; abusing the osm
        // tile servers will get you banned based on this string.
        subscribe(ChannelName.Location)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        map = view.findViewById(R.id.view_map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        controller = map.controller
        controller.setZoom(21.0)
        overlay = MyLocationNewOverlay(map)
        overlay.enableAutoStop = false
        overlay.enableMyLocation()
        overlay.disableFollowLocation()
        map.overlays.add(overlay)
        lockButton = view.findViewById(R.id.button_lock)
        lockButton.setBackgroundResource(R.drawable.ic_baseline_navigation_followmode_inactive)
        lockButton.setOnClickListener {
            isFollowModeActive = !isFollowModeActive
            if (isFollowModeActive) {
                map.mapOrientation = 360 - bearing
                overlay.enableFollowLocation()
                lockButton.setBackgroundResource(R.drawable.ic_baseline_navigation_followmode_active)
            } else {
                map.mapOrientation = 0F
                overlay.disableFollowLocation()
                lockButton.setBackgroundResource(R.drawable.ic_baseline_navigation_followmode_inactive)
            }
        }
        Log.i("In onView:", "Hi")
        return view
    }

    override fun onDetach() {
        super.onDetach()
        map.onDetach()
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

    /**
     * @author Erik
     * @author Jonathan
     **/
    private var isFirstLocationUpdate = true
    private fun onLocationUpdate(location: Location) {
        if (isFirstLocationUpdate) {
            controller.setCenter(GeoPoint(location))
            isFirstLocationUpdate = false
        }
        //check if route was completed last time and then reset the route
        //Not well written. This check is not optimal.
        // It's better if MainTimer could call functions from here and we were saving routes appropriately.
        // It will mimic appropriate behaviour for now.
        /*

        if (routeCompleted && mapFragmentInfo.isRecording) {
            mapFragmentInfo.recordedRoute = Route(mutableListOf())
            routeCompleted = false
        }

        //check if previous location is too far away to record.
        if (mapFragmentInfo.recordedRoute.coordinates.size > 0) {
            val distanceFromPreviousRecordedPointSquared = Distance.getSquaredDistanceToPoint(
                location.latitude,
                location.longitude,
                mapFragmentInfo.recordedRoute.coordinates.last().second.latitude,
                mapFragmentInfo.recordedRoute.coordinates.last().second.longitude
            )

            if (distanceFromPreviousRecordedPointSquared > distanceThresholdToBreakRecording && mapFragmentInfo.isRecording) {
                //TODO: Save away route
                mapFragmentInfo.recordedRoute = Route(mutableListOf())
            }
        }
        */
        this.location = location
        //FollowMode
        if (isFollowModeActive)
            map.mapOrientation = 360 - location.bearing
        //Record
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            recordedRoute.coordinates.add(
                Pair(
                    simpleDateFormat.format(Date()),
                    GeoPoint(location)
                )
            )
        }
        /*
        if (mapFragmentInfo.shouldStopRecording) {
            mapFragmentInfo.isRecording = false
            mapFragmentInfo.shouldStopRecording = false
            routeCompleted = true
            //TODO: Save away route AND display the full recorded route until new route is started.
        }
        */
        redrawWalkedRoute()
    }

    /**
     * @author Erik
     **/
    private fun redrawWalkedRoute() {
        map.overlays.remove(routeLineDrawn)
        routeLineDrawn = drawRoute(recordedRoute)
    }

    private lateinit var oldRoute: Route
    private fun updateRoute(new: RouteData, old: RouteData?) =
        map.overlays.remove(Polyline() fromRoute oldRoute)
            .also { drawRoute(new) }
            .also { oldRoute = new }


    /**
     * @author Erik
     * @author Jonathan
     * @author Felix
     **/
    private fun drawRoute(r: Route): Polyline {
        val line = Polyline() fromRoute r
        map.overlays.add(line)
        return line
    }

    private infix fun Polyline.fromRoute(route: Route): Polyline {
        val geoPoints = arrayListOf<GeoPoint>()
        route.coordinates.forEach { geoPoints.add(it.second) }
        var polyline = Polyline()
        polyline.setPoints(geoPoints)
        return polyline
    }

    override fun onUpdate(source: ChannelName, message: Message<*>) {
        with(message) {
            when (source) {
                ChannelName.Location -> {
                    if (payload is Location) onLocationUpdate(payload)
                    else throw IllegalPayloadException("", "")
                }
                ChannelName.Route -> {
                    with(payload as Pair<*, *>){
                        updateRoute(first as RouteData, second as RouteData?)
                    }
                }
                else ->
                    throw IllegalArgumentException("subscription error in mapfragment:" +
                            "\n channel = ${source.name}")
            }
        }.also {
            Log.d("Mapfragment, onupdate", "source: ${source.name}")
        }
    }
}
// TODO: 2021-10-03
// Skapa en generisk drawRoute funktion som tar in en Klass med Settings
// (med underliggande klass(er) LineSettings)
// Utöver detta separera funktionen recordRoute (den som användaren spelar in)
// och drawRoute (den som användaren hämtat "för att följa" (tillkallas separat)

/**
 * @author Erik
 * @author Jonathan
 * @author Felix
 **/
data class Route(var coordinates: MutableList<Pair<String, GeoPoint>>) {

}