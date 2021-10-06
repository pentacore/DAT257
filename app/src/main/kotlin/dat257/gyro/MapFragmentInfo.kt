package dat257.gyro

import android.location.Location
//Could be shifted to a ViewModel for livedata and observability if needed
/**
 * @author Erik
 */
data class MapFragmentInfo(var location : Location){
    var mapLoaded = false
    var isFollowModeActive = false
}
