package dat257.gyro.models

import dat257.gyro.data.Coordinate
import dat257.gyro.data.CoordinateRecord
import dat257.gyro.patterns.publish_subscribe.Message
import dat257.gyro.patterns.publish_subscribe.Subscriber
import java.time.Clock

/**
 * model for handling coordinates
 */
class Locator(val current: Coordinate,val passed: CoordinateRecord,val clock: Clock): Subscriber{
    init {

    }

    override fun subscribe(channel: String, subscriber: Subscriber) {

    }

    override fun onUpdate(channel: String, message: Message<*>) {
        TODO("Not yet implemented")
    }
}