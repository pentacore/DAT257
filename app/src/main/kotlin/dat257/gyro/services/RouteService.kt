package dat257.gyro.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.IBinder
import dat257.gyro.IllegalPayloadException
import dat257.gyro.patterns.publisherSubscriber.*
import org.osmdroid.util.GeoPoint
import java.text.SimpleDateFormat
import java.util.*


class RouteService : Service(), Publisher, Subscriber {
    override fun onBind(p0: Intent?): IBinder? = null
    private val routeModel: RouteModel = RouteModel()

    init {
        createChannel(ChannelName.Route)
        subscribe(ChannelName.Location)
    }


    override fun onUpdate(source: ChannelName, message: Message<*>) =
        with(message) {
            when (source) {
                ChannelName.Location -> {
                    if (payload is Location) {
                        onLocationUpdate(payload)
                    } else throw IllegalPayloadException("asd", "asd")
                }
            }
            throw IllegalPayloadException("asd", "asd")
        }

    private fun onLocationUpdate(location: Location){
        val old = routeModel.routeData
        routeModel.process(location)
        val new = routeModel.routeData
        publish(
            ChannelName.Route,
            Message(
                ChannelSchema.Instruction.Default,
                Pair(
                    old,
                    new
                )
            )
        )
    }
}

typealias GeoTime = Pair<String, GeoPoint>

class RouteModel() {

    internal var routeData : RouteData = RouteData()
    get() = routeData.copy()
    fun process(location: Location) = store(reformat(location))

    private fun reformat(location: Location): GeoTime {
        @SuppressLint("SimpleDateFormat")
        val dateString: String =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(Calendar.getInstance().time)
        return GeoTime(dateString,GeoPoint(location))
    }
    private fun store(geoTime: GeoTime) = routeData.geoTimes.add(geoTime)
}

data class RouteData(val geoTimes : MutableList<GeoTime> = mutableListOf<GeoTime>())