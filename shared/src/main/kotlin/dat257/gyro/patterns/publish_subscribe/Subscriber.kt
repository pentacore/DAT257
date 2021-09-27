package dat257.gyro.patterns.publish_subscribe

import java.util.*


interface Subscriber {
    fun subscribe(channel: String)
    fun onUpdate(channel: UUID, message: Message<*>)
}