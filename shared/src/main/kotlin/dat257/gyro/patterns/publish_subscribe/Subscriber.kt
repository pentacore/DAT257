package dat257.gyro.patterns.publish_subscribe

import java.util.*


abstract class Subscriber(){
    fun onUpdate(channel: String, message: Message<*>)
}