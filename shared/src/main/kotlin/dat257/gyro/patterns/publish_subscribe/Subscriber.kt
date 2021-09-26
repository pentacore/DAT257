package dat257.gyro.patterns.publish_subscribe


interface Subscriber {
    fun subscribe(c: Channel)
    fun onUpdate(channel: Channel, message: Message<*>)
}