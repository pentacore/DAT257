package dat257.gyro.patterns.publisherSubscriber

import dat257.gyro.BrokerActionException

/**
 * @author Jonathan
 */
enum class ChannelName {
    Timer,
    Location,
    Route,
    RouteServiceInternal
}

/**
 * @author Jonathan
 * standardstruktur för ett schema:
 * Object som extendar schemat blir kanalnamn
 * instruktioner ger information till observers ang vad dem ska göra med payload
 * Skicka med default ifall det inte finns en uppsättning instruktioner för kanalen
 */
sealed class ChannelSchema(
){
    sealed class Instruction{
        object Default: Instruction()
    }
}

object RouteServiceSchema: ChannelSchema(){
    object RouteInstructions: Instruction() {
        object Replace
    }
}



/**
 * @author Jonathan
 * Accessor for sending messages
 * Acts as layer between the exchange and the subscribers
 */
class Broker {
    /**
     * @author Jonathan
     * Can be called without obj declaration
     */
    companion object {
        private val channels: MutableMap<ChannelName, Channel> = mutableMapOf()
        fun createChannel(
            publisher: Publisher,
            name: ChannelName
        ) = with(channels) {
            if (containsKey(name)) throw BrokerActionException(
                BrokerActionException.CreateChannel("Channel already exists")
            )
            else this[name] = Channel(publisher)
        }

        /**
         * @author Jonathan
         */
        fun share(
            name: ChannelName,
            message: Message<*>
        ) = with(channels){
            get(name)?.subscribers?.forEach { it.onUpdate(name,message) }
        }

        /**
         * @author Jonathan
         */
        fun subscribe(s: Subscriber, c: ChannelName) =
            with(channels) {
                this[c]?.subscribers?.add(s) ?: throw BrokerActionException(
                    BrokerActionException.Subscribe("Channel does not exist")
                )
            }
    }
}
