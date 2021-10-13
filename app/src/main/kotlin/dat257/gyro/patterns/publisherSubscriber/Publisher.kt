package dat257.gyro.patterns.publisherSubscriber

import android.location.Location

/**
 * @author Jonathan Sargent
 * Describes party that publishes and creates channels of communications
 */
interface Publisher {

    fun createChannel(name: ChannelName) = wireChannel(this, name)

    /**
     * Creates new line of communication
     */
    private fun wireChannel(publisher: Publisher, name: ChannelName) =
        Broker.createChannel(this, name)

    /**
     * publish a message
     */
    fun publish(name: ChannelName, msg: Message<*>) =
        Broker.share(name, msg)
}