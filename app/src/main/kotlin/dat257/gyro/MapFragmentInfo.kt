package dat257.gyro

import android.location.Location
import org.osmdroid.util.GeoPoint

//Could be shifted to a ViewModel for livedata and observability if needed
/**
 * @author Erik
 */
data class MapFragmentInfo(var location : Location?){
    var shouldStopRecording = false
    var mapLoaded = false
    var isFollowModeActive = false
    var isRecording = false
    var recordedRoute = Route(mutableListOf<Pair<String,GeoPoint>>())
}
