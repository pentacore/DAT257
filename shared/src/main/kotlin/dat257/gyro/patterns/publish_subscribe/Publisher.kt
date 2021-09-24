package dat257.gyro.patterns.publish_subscribe

interface Publisher {
    fun addChannel(Channel c)
    fun update(Channel c)
}