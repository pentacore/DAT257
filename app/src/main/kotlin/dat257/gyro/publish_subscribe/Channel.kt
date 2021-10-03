package dat257.gyro.publish_subscribe

import java.util.*

/**
 * A way of categorizing who to send to, channels are not to be named after class names but rather
 * the topic of information to provide to promote decoupling
 */

data class Channel(
    val name: ChannelNames,
    val publisher: Publisher,
    private val broker: Broker,
    val subscribers: MutableList<Subscriber> = mutableListOf()
) {

}

enum class ChannelNames {
    Location,
    Timer
}