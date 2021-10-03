package dat257.gyro.publish_subscribe


interface Subscriber {
    fun subscribe(channel: String, subscriber: Subscriber)
    fun onUpdate(channel: String, message: Message<*>)
}