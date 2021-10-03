package dat257.gyro.publish_subscribe

interface BrokerOwner {
    fun obtainBroker(new : Broker)
}