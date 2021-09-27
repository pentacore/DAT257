package dat257.gyro.patterns.publish_subscribe

import java.util.*


interface Subscriber {
    fun subscribe(channel: String, subscriber: Subscriber)
    fun onUpdate(channel: String, message: Message<*>)
}