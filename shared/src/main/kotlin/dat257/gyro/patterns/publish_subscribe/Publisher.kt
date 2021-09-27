package dat257.gyro.patterns.publish_subscribe

import java.util.*

/**
 * Describes party that publishes and creates channels of communications
 */
interface Publisher {

   /**
    * Creates new line of communication, returns UUID asscociated with identifying the channel
    */
   public fun createChannel(publisher: Publisher): UUID

   /**
    * publish a message
    */
   fun publish(name: UUID, msg: Message<*>): Nothing
}