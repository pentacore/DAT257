package dat257.gyro.publish_subscribe
import android.util.Log
import kotlin.NoSuchElementException


/**
 * The layer of abstraction placed between publishers and
 * subscribers to keep them from knowing each others, for increased decoupling
 * The string is for password
 */
class Broker(
    private val channels: MutableList<Channel> = mutableListOf<Channel>(),
    val publishers: MutableList<Publisher> = mutableListOf<Publisher>()
) {

    /* För när vi har tid, asynkrona coola grejjor
    private fun shareFlow(name: ChannelNames, message: Message<*>) = flow<Pair<Message<*>, Subscriber>> {
        Log.i("ShareFlow", "sharing")
        channels[name]?.subscribers?.forEach { emit(Pair(message, it)) }
            ?: throw NoSuchElementException("No such channel") // borde specifieras senare
    }*/

    /**
     * shares a message with the subscribers of a channel
     */
    fun share(name: ChannelNames, message: Message<*>) =
        channels.firstOrNull { it.name == name }
            ?.let { channel ->
                if (channel.subscribers.size == 0)
                    throw NoSuchElementException("Channel has no subscribers")
                else
                    channel.subscribers
                        .forEach { it.onUpdate(name, message) }
            }
            ?: throw NoSuchElementException("No such channel")

    /**
     * Shares a message across multiple channels
     */
    fun shareMultiple(channels: List<ChannelNames>, message: Message<*>) =
        channels.forEach { share(it, message) }

    /**
     * join the list of subscribers to recieve events on channel updates
     */
    fun subscribe(channel: ChannelNames, subscriber: Subscriber) =
        channels.firstOrNull { it.name == channel }?.subscribers?.add(subscriber)
            ?: throw NoSuchElementException("No such channel")

    /**
     * creates new channel
     */
    fun wireChannel(publisher: Publisher, name: ChannelNames) =
        channels.firstOrNull { it.name == name }
            ?.let { throw Exception("Channel already exist") }
            ?: channels.add(
                Channel(name, publisher, this)
            )
}




