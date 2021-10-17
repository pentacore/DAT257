package dat257.gyro
import android.util.Log

/**
 * @author Jonathan
 */
class BrokerActionException(failure: BrokerFailure) : Exception() {
    sealed class BrokerFailure(val cause: String)
    class CreateChannel(cause: String) : BrokerFailure("$cause\n Action: Create Channel")
    class Subscribe(cause: String) : BrokerFailure("$cause\n Action: Subscribe")
}


/**
 * @author Jonathan
 */
class IllegalPayloadException(
    required: String,
    real: String
) : Exception("Illegal payload type: $real, \n the required type is: $required") {
    init{
        Log.d("Custom exception","Illegall payload, req:$required, real: $real",this)
    }
}