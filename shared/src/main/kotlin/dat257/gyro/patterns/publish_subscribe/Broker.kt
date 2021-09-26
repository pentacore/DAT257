package dat257.gyro.patterns.publish_subscribe

/**
 * The layer of abstraction placed between publishers and
 * subscribers to keep them from knowing each others, for increased decoupling
 */
abstract class Broker(var channels: MutableList<Channel>) {

    /**
     * shares a message with the subscribers of a channel
     */
    fun share(channel: Channel, message: Message<*>) : Status = if (channels.contains(channel))
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
            .also { log() }

    fun wireChannel(name: String, access: String, publisher: Publisher) =

    abstract fun log(function: String, status: Status, details: String)
}

enum class Status{
    Ongoing,
    Success,
    Failure,
}


