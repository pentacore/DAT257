package dat257.gyro.patterns.publish_subscribe

import java.util.*
import kotlin.collections.HashMap

/**
 * The layer of abstraction placed between publishers and
 * subscribers to keep them from knowing each others, for increased decoupling
 * The string is for password
 */
class Broker(var channels: MutableMap<String,Channel>) {
    companion object{
        infix fun Subscriber.subscribe(channel: Channel)
    }

    val authorization : MutableMap<UUID,String> = mutableMapOf<UUID,String>()

    /**
     * shares a message with the subscribers of a channel
     */
    fun share(name: String, message: Message<*>) = if (channels.containsKey(name)) channels[name].getSubscribers()


    /**
     * Shares a message across multiple channels
     */
    fun shareMultiple(channels : List<String>, message: Message<*>) =
        channels.forEach{share(it,message)}

    /**
     * join the list of subscribers to recieve events on channel updates
     */
    fun subscribe(channel: String, subscriber: Subscriber): Permission =
        if (channel in channels){ channel.subscribe(subscriber,password)} else {
            Permission.Denied
        }

    /**
     * creates new channel with admin-rights
     */
    fun wireChannel(publisher: Publisher,name: String) {
        val adminAccess = UUID.randomUUID()
        val newChannel = Channel(name,adminAccess,publisher)
        authorization[adminAccess] = name
        channels[name] =
    }

    // Todo add some logger or some way of creating a registry
    // make async ?
    // unsure about letting channels know what leaning towards not
}

