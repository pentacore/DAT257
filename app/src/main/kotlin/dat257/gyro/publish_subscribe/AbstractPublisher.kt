package dat257.gyro.publish_subscribe

abstract class AbstractPublisher(private val broker: Broker) : Publisher {

    override fun publish(name: ChannelNames, msg: Message<*>) = broker.share(name, msg)
    override fun createChannel(publisher: Publisher, name: ChannelNames): Boolean =
        broker.wireChannel(this, name)
            .also { channels.add(name) }
}