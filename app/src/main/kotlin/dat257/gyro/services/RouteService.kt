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
import java.text.SimpleDateFormat
import java.util.*

class RouteService : Service(), Publisher {
    override fun onBind(p0: Intent?): IBinder? = null
    private val routeModel: RouteModel

    init {
        createChannel(ChannelName.Route)
        routeModel = RouteModel()
    }

    internal fun retrieve(location: Location): Nothing = TODO()
}


typealias GeoTime = Pair<Location, String>

class RouteModel() : Subscriber {
    private val routeData = RouteData()

    init {
        subscribe(channel = ChannelName.Location)
    }

    override fun onUpdate(source: ChannelName, message: Message<*>) {
        when (source) {
            ChannelName.Location -> {
                with(message) {
                    if (payload is Location) {
                        @SuppressLint("SimpleDateFormat")
                        val dateString: String =
                            SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .format(Calendar.getInstance().time)
                        routeData.append(GeoTime(payload, dateString))
                    } else throw IllegalPayloadException("", "")
                }
            }
            else -> {
                throw IllegalPayloadException("", "")
            }
        }
    }

    data class RouteData(
        private var record: MutableList<GeoTime> = mutableListOf()
    ) {
        fun append(geoTime: GeoTime) = record.add(geoTime)
    }
}

