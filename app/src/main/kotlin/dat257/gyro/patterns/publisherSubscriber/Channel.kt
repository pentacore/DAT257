package dat257.gyro.patterns.publisherSubscriber


/**
 * A way of categorizing who to send to, channels are not to be named after class names but rather
 * the topic of information to provide to promote decoupling
 * @author Jonathan
 */

data class Channel(
    val publisher: Publisher,
    val subscribers: MutableList<Subscriber> = mutableListOf()
)