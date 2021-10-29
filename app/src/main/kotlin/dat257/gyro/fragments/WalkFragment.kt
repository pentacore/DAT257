package dat257.gyro.fragments

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import dat257.gyro.R
import dat257.gyro.model.RecordingControllerInstruction
import dat257.gyro.patterns.publisherSubscriber.ChannelName
import dat257.gyro.patterns.publisherSubscriber.Message
import dat257.gyro.patterns.publisherSubscriber.Publisher
import dat257.gyro.patterns.publisherSubscriber.Subscriber
import dat257.gyro.services.TimerService
import kotlin.math.roundToInt

class WalkFragment : Fragment(), Publisher, Subscriber {
    private lateinit var walkPlay: Button
    private lateinit var walkReset: Button
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_walk, container, false)
        walkPlay = view.findViewById(R.id.startStopButton)
        walkPlay.setBackgroundResource(R.drawable.ic_baseline_play_circle_24)
        walkReset = view.findViewById(R.id.resetButton)
        walkReset.setBackgroundResource(R.drawable.ic_baseline_stop_circle_24)
        subscribe(ChannelName.Distance)
        walkPlay.setOnClickListener {
            childFragmentManager.beginTransaction().apply {
                timerStarted = !timerStarted
                if (timerStarted) {
                    walkPlay.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24)
                    publish(
                        ChannelName.RecordingControl,
                        Message(RecordingControllerInstruction.Play)
                    )
                    startTimer()
                } else {
                    walkPlay.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24)
                    publish(
                        ChannelName.RecordingControl,
                        Message(RecordingControllerInstruction.Pause)
                    )
                    stopTimer()
                }

                commit()
            }
            walkReset.setOnClickListener {
                publish(ChannelName.RecordingControl, Message(RecordingControllerInstruction.Stop))
                resetTimer()
                updateDistanceString(0.0)
                timerStarted=false
                walkPlay.setBackgroundResource(R.drawable.ic_baseline_play_circle_24)
            }
        }

        serviceIntent = Intent(activity?.applicationContext, TimerService::class.java)
        requireActivity().registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
        return view
    }

    override fun onUpdate(source: ChannelName, message: Message<*>) {
        when (source) {
            ChannelName.Distance -> updateDistanceString(message.payload as Double)
            else -> Log.e("WalkFragment/onUpdate: ", "Unimplemented Channel Source")
        }
    }

    private fun updateDistanceString(d: Double) {
        val distance = String.format("%.3f", d)
        view?.findViewById<TextView>(R.id.distance_string)?.text = distance
    }


    fun updateTimerString(){
        view?.findViewById<TextView>(R.id.time_string)?.text=getTimeStringFromDouble(time)


    }
    private fun resetTimer() {
        stopTimer()
        time = 0.0
        updateTimerString()
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        activity?.startService(serviceIntent)
        view?.findViewById<com.google.android.material.button.MaterialButton>(R.id.startStopButton)
            ?.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24)
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    fun stopTimer() {
        activity?.stopService(serviceIntent)
        view?.findViewById<com.google.android.material.button.MaterialButton>(R.id.startStopButton)
            ?.setBackgroundResource(R.drawable.ic_baseline_play_circle_24)
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            updateTimerString()
        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String =
        String.format("%02d:%02d:%02d", hour, min, sec)
}

