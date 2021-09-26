package dat257.gyro.patterns.publish_subscribe

interface Publisher {
   public fun createChannel(c: Channel): Nothing = TODO()
   fun update(c: Channel): Nothing = TODO()
}