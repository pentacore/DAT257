package dat257.gyro.model

import android.location.Location
import kotlinx.serialization.Serializable
import org.osmdroid.util.GeoPoint

@Serializable
data class Coordinate(val latitude: Double, val longitude: Double, val altitude: Double) {
    constructor(
        location:Location
    ) : this(
        location.latitude,
        location.longitude,
        location.altitude
    )

    fun toGeoPoint(): GeoPoint {
        return GeoPoint(latitude, longitude, altitude)
    }

}
