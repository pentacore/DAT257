package dat257.gyro.publish_subscribe

/**
 * @author Jonathan Sargent
 * Describes party that publishes and creates channels of communications
 */
interface Publisher {
   val channels: MutableList<ChannelNames>
   /**
    * Creates new line of communication, returns UUID asscociated with identifying the channel
    */
   fun createChannel(publisher: Publisher, name: ChannelNames) : Boolean

   /**
    * publish a message
    */
   fun publish(name: ChannelNames, msg: Message<*>)

}