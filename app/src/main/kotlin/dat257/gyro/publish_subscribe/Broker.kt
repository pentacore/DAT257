package dat257.gyro.publish_subscribe

import arrow.core.*
import java.util.*
import kotlin.collections.HashMap


/**
 * The layer of abstraction placed between publishers and
 * subscribers to keep them from knowing each others, for increased decoupling
 * The string is for password
 */
class Broker(
    private val owner: BrokerOwner,
    private val init: Boolean,
    private val channels: Map<String, Channel>,
    private val authorization: Map<UUID, String>,
    ) {

    init {
        if (!init) owner.obtainBroker(this)
    }


    fun callPurely(mutation: () -> Broker): Broker =
        mutation.invoke().also { owner.obtainBroker(this) }


    /**
     * shares a message with the subscribers of a channel
     */
    fun share(name: String, message: Message<*>): Option<Exception> =
        if (!channels.containsKey(name)) Some(IllegalArgumentException("no"))
        else None


    /**
     * Shares a message across multiple channels
     */
    fun shareMultiple(channels: List<String>, message: Message<*>) =
        channels.forEach { share(it, message) }

    /**
     * join the list of subscribers to recieve events on channel updates
     */
    fun subscribe(channel: String, subscriber: Subscriber): Either<Exception, BrokerStatus> =
        Either.conditionally(
            channels.containsKey(channel),
            fun() = IllegalArgumentException("no"),
            fun() = BrokerStatus.Initiated()
        )


    // error not found
    // success
    /*
     val maybeChannel : Option<Channel> = Option.fromNullable(channels[channel])
     maybeChannel.getOrElse { Log.d("Channel", "No such channel found") }
*/

    /**
     * creates new channel with admin-rights
     */
    fun wireChannel(publisher: Publisher, name: String) {
        val adminAccess = UUID.randomUUID()
        val newChannel = Channel(name, adminAccess, publisher)
        val uuids = authorization.keys
        val channelSet = authorization.values
        val names = channels.keys

    }

    // Todo add some logger or some way of creating a registry
    // make async ?
}


sealed class BrokerStatus(status: String) {
    class Queued : BrokerStatus("Queued")
    class Initiated : BrokerStatus("Initiated")
    class Pending : BrokerStatus("Pending")
}


