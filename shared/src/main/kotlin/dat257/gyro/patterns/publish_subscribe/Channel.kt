package dat257.gyro.patterns.publish_subscribe

import java.util.*
import java.util.logging.Logger

/**
 * A way of categorizing who to send to, channels are not to be named after class names but rather
 * the topic of information to provide to promote decoupling
 */

class Channel(
    private val name: String,
    private val adminAccessPass: UUID,
    private val publisher: Publisher
) {
    // modifiera get för password den ska inte existera
    private val subscribers: MutableList<Subscriber> = mutableListOf()

    fun getSubscribers(password: UUID): MutableList<Subscriber> =
        if (password == adminAccessPass) subscribers
        else throw IllegalAccessException("Attempted to access subscribers with wrong password")

    fun authSubscriber(): Permission =
        Permission.Accepted

    fun subscribe(s: Subscriber, adminPass: UUID): Permission =
        if (adminPass == adminAccessPass) {
            subscribers.add(s)
            Permission.Accepted
        } else {
            Permission.Denied
        }
}

    // det här borde definitvt vara exceptions
    enum class Permission {
        Denied,
        Accepted
    }
