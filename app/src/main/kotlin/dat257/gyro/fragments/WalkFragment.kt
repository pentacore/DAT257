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
import dat257.gyro.R
import dat257.gyro.model.RecordingControllerInstruction
import dat257.gyro.patterns.publisherSubscriber.ChannelName
import dat257.gyro.patterns.publisherSubscriber.Message
import dat257.gyro.patterns.publisherSubscriber.Publisher
import dat257.gyro.patterns.publisherSubscriber.Subscriber
import dat257.gyro.services.TimerService
import kotlin.math.roundToInt

class WalkFragment : Fragment(), Publisher, Subscriber {
    //The channel that this publishes to is currently being made in LocationService. This should
    //Probably change to be a central channel creator - or by changing the pattern.
    //  private lateinit var walkMinimize: Button
    private lateinit var walkPlay: Button
    private lateinit var walkReset: Button
    private var timerStarted = false
    private var distanceOfRoute = 0.0
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_walk, container, false)
        // walkMinimize = view.findViewById(R.id.walkMinimize)
        // walkMinimize.setBackgroundResource(R.drawable.ic_baseline_minimize_24)
        walkPlay = view.findViewById(R.id.startStopButton)
        walkPlay.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
        walkReset = view.findViewById(R.id.resetButton)
        walkReset.setBackgroundResource(R.drawable.ic_baseline_refresh_24)
        subscribe(ChannelName.Distance)
        walkPlay.setOnClickListener {
            childFragmentManager.beginTransaction().apply {
                Log.i("yaay", "snel hest")
                timerStarted = !timerStarted
                // walkButton.setBa
                if (timerStarted) {
                    walkPlay.setBackgroundResource(R.drawable.ic_baseline_pause_24)
                    publish(
                        ChannelName.RecordingControl,
                        Message(RecordingControllerInstruction.Play)
                    )
                    startTimer()
                } else {
                    walkPlay.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
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
            }
        }

        serviceIntent = Intent(activity?.applicationContext, TimerService::class.java)
        requireActivity().registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
        return view


    }

    override fun onUpdate(source: ChannelName, message: Message<*>) {
        Log.i("In Walkfragmentupdate:", message.payload.toString())
        Log.i("In Walkfragmentupdate:", getTimeStringFromDouble(time))
        updateDistanceString()
    }

    fun updateDistanceString() {
        //TODO: updateDistanceString
    }

    fun resetTimer() {
        stopTimer()
        time = 0.0
    }

    fun startStopTimer() {
        if (timerStarted)
            stopTimer()
        else
            startTimer()
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        activity?.startService(serviceIntent)
        //view?.findViewById<com.google.android.material.button.MaterialButton>(R.id.startStopButton)?.text = "stop"
        //binding.startStopButton.text = "Stop"
        //binding.startStopButton.icon = getDrawable(R.drawable.ic_baseline_pause_24)
        // HEJ JAG HETER JONATHAN
        view?.findViewById<com.google.android.material.button.MaterialButton>(R.id.startStopButton)
            ?.setBackgroundResource(R.drawable.ic_baseline_pause_24)
        //mainActivity.mapFragmentInfo.isRecording=true
        //timerStarted = true
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    fun stopTimer() {
        // stopService(serviceIntent)
        activity?.stopService(serviceIntent)
        //view?.findViewById<com.google.android.material.button.MaterialButton>(R.id.startStopButton)?.text = "start"
        view?.findViewById<com.google.android.material.button.MaterialButton>(R.id.startStopButton)
            ?.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
        // binding.startStopButton.text = "Start"
        //binding.startStopButton.icon = getDrawable(R.drawable.ic_baseline_play_arrow_24)
        //mainActivity.mapFragmentInfo.isRecording=false
        //timerStarted = false
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            //view?.findViewById<TextView>(R.id.timeTV)?.text = getTimeStringFromDouble(time)
            // binding.timeTV.text = getTimeStringFromDouble(time)
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

