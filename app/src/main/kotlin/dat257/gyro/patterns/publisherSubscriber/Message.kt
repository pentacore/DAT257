package dat257.gyro.patterns.publisherSubscriber

data class Message<T>(
    val instruction: ChannelSchema.Instruction?,
    val payload: T
    )
