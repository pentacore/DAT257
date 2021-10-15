package dat257.gyro.patterns.publisherSubscriber

/**
 * @author Jonathan
 */
interface Subscriber {
    fun subscribe(channel: ChannelName) = Broker.subscribe(this,channel)
    fun onUpdate(source: ChannelName, message: Message<*>)
}