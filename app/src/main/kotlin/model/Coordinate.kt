package model

import kotlinx.serialization.Serializable
import org.osmdroid.util.GeoPoint

@Serializable
data class Coordinate(val latitude: Double, val longitude: Double, val altitude: Double) {

    fun toGeoPoint(): GeoPoint {
        return GeoPoint(latitude, longitude, altitude)
    }

}
