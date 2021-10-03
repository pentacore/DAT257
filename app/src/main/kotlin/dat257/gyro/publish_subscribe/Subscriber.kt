package dat257.gyro.publish_subscribe

/**
 * @author Jonathan
 */
interface Subscriber {
    fun subscribe(channel: ChannelNames, subscriber: Subscriber)
    fun onUpdate(channel: ChannelNames, message: Message<*>)
}