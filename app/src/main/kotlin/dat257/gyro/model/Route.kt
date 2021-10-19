package dat257.gyro.model

import android.content.Context
import dat257.gyro.Jsonable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin


/**
 * @author Erik
 * @author Jonathan
 * @author Felix
 **/
@ExperimentalSerializationApi
@Serializable
data class Route(var coordinates: MutableList<Pair<String, Coordinate>>): Jsonable {

    override fun fromJson(applicationContext: Context, fileName: String): Route {
        val input = applicationContext.openFileInput(fileName)
        val route = Json.decodeFromStream(
            serializer(),
            input
        )
        input.close()
        return route
    }

    override fun toJson(applicationContext: Context, fileName: String) {
        val output = applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE)
        Json.encodeToStream(
            this,
            output
        )
        output.close()
    }

    /**
     * @author Erik
     */
    fun getDistance(): Double{
        var distanceToReturn = 0.0
        for(i in 0 until coordinates.size-1){
            distanceToReturn += calcDistance(coordinates[i].second.latitude, coordinates[i].second.longitude, coordinates[i+1].second.latitude, coordinates[i+1].second.longitude)
        }
        return distanceToReturn
    }

    /**
     * @author Erik
     */
    private fun calcDistance(lat1:Double, lon1:Double, lat2:Double, lon2:Double): Double{
        //Return km
        val theta: Double = lon1 - lon2
        var dist = (sin(deg2rad(lat1))
                * sin(deg2rad(lat2))
                + cos(deg2rad(lat1))
                * cos(deg2rad(lat2))
                * cos(deg2rad(theta)))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515 * 1.60934 //1.60934 converts from American miles to km
        return dist
    }
    private fun deg2rad(deg : Double):Double{
        return deg*Math.PI/180
    }
    private fun rad2deg(rad : Double):Double{
        return rad*180/Math.PI
    }
}