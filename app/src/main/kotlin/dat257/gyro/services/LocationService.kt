package dat257.gyro.services

import dat257.gyro.publish_subscribe.*

class LocationService(broker: Broker) : AbstractPublisher(broker = broker) {
    init{
        createChannel(this,ChannelNames.Location)

    }


}