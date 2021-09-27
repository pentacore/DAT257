package dat257.gyro.patterns.publish_subscribe

import java.util.*
import kotlin.collections.HashMap

/**
 * The layer of abstraction placed between publishers and
 * subscribers to keep them from knowing each others, for increased decoupling
 * The string is for password
 */
abstract class Broker(var channels: HashMap<UUID,Channel>) {

    /**
     * shares a message with the subscribers of a channel
     */
    fun share(name: UUID, message: Message<*>) : Status =
        if (channels.contains(channel))
        channel.getSubscribers("temp")

    /**
     * Shares a message across multiple channels
     */
    fun shareMultiple(channels : List<Channel>, message: Message<*>) =
        channels.forEach{share(it,message)}

    /**
     * join the list of subscribers to recieve events on channel updates
     */
    fun subscribe(password: String, channel: Channel, subscriber: Subscriber): Permission =
        if (channel in channels){ channel.subscribe(subscriber,password)} else {
            Permission.Denied
        }

    /**
     * creates new channel with admin-rights
     */
    fun wireChannel(name: String, publisher: Publisher){
        val adminAccess = UUID.randomUUID() // Only broker has adminrights
        channels.put(adminAccess,Channel(name,adminAccess))
    }

    // Todo add some logger or some way of creating a registry
    // make async ?
    // unsure about letting channels know what leaning towards not
}

enum class Status{
    Ongoing,
    Success,
    Failure,
}


