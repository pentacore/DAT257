package dat257.gyro.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.IBinder
import dat257.gyro.IllegalPayloadException
import dat257.gyro.patterns.publisherSubscriber.ChannelName
import dat257.gyro.patterns.publisherSubscriber.Message
import dat257.gyro.patterns.publisherSubscriber.Publisher
import dat257.gyro.patterns.publisherSubscriber.Subscriber
import org.osmdroid.util.GeoPoint
import java.text.SimpleDateFormat
import java.util.*

class RouteService : Service(), Publisher, Subscriber {
    override fun onBind(p0: Intent?): IBinder? = null
    private val routeModel: RouteModel

    init {
        createChannel(ChannelName.Route)
        routeModel = RouteModel()
    }

    internal fun retrieve(location: Location): RouteModel.RouteData = TODO()
    override fun onUpdate(source: ChannelName, message: Message<*>) {
        when (source) {
            ChannelName.Location -> {
                with(message) {
                    if (payload is Location) {
                        @SuppressLint("SimpleDateFormat")
                        val dateString: String =
                            SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .format(Calendar.getInstance().time)
                        routeData.append(
                            GeoTime(
                                GeoPoint(payload),
                                dateString
                            )
                        )
                    } else throw IllegalPayloadException("", "")
                }
            }
            else -> {
                throw IllegalPayloadException("", "")
            }
        }
    }
}


typealias GeoTime = Pair<GeoPoint, String>

class RouteModel() : Subscriber {
    private val routeData = RouteData()

    init {
        subscribe(channel = ChannelName.Location)
    }


    override fun onUpdate(source: ChannelName, message: Message<*>) =

    data class RouteData(
        private var record: MutableList<GeoTime> = mutableListOf()
    ) {
        fun refresh(geoTime: GeoTime): MutableList<GeoTime> = record.apply { add(geoTime) }
    }
}

