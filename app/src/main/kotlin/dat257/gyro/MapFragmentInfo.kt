package dat257.gyro

import android.location.Location
//Could be shifted to a ViewModel for livedata and observability if needed
data class MapFragmentInfo(var location : Location){
    var mapLoaded = false
}
