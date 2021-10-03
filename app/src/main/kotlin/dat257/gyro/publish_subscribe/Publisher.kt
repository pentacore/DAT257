package dat257.gyro.publish_subscribe

/**
 * @author Jonathan Sargent
 * Describes party that publishes and creates channels of communications
 */
interface Publisher {
   /**
    * Creates new line of communication, returns UUID asscociated with identifying the channel
    */
   fun createChannel(publisher: Publisher, name: String): Nothing

   /**
    * publish a message
    */
   fun publish(name: String, msg: Message<*>): Nothing

}