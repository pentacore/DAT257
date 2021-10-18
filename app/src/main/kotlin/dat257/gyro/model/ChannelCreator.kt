package dat257.gyro.model

import dat257.gyro.patterns.publisherSubscriber.ChannelName
import dat257.gyro.patterns.publisherSubscriber.Publisher

/**
 * @author Erik
 */
class ChannelCreator() : Publisher {
    init {
        createChannel(ChannelName.Location)
        createChannel(ChannelName.RecordingControl)
        createChannel(ChannelName.Distance)
    }
}