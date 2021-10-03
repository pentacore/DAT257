package dat257.gyro.services
import android.app.Service
import android.content.Intent
import android.os.IBinder
import dat257.gyro.publish_subscribe.*
import java.util.*


class TimerService(val broker: Broker) : Service(), Publisher
{
    override fun onBind(p0: Intent?): IBinder? = null

    private val timer = Timer()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
    {
        val time = intent.getDoubleExtra(TIME_EXTRA, 0.0)
        timer.scheduleAtFixedRate(TimeTask(time), 0, 1000)
        return START_NOT_STICKY
    }

    override fun onDestroy()
    {
        timer.cancel()
        super.onDestroy()
    }

    private inner class TimeTask(private var time: Double) : TimerTask()
    {
        override fun run()
        {
            val intent = Intent(TIMER_UPDATED)
            intent.putExtra(TIME_EXTRA, time)
            sendBroadcast(intent)
            time++
        }
    }

    companion object
    {
        const val TIMER_UPDATED = "timerUpdated"
        const val TIME_EXTRA = "timeExtra"
    }

    override fun createChannel(publisher: Publisher, name: ChannelNames): Boolean = broker.wireChannel(this,name)

    override fun publish(name: ChannelNames, msg: Message<*>) {
        TODO("Not yet implemented")
    }

}