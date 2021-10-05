package dat257.gyro

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.math.roundToInt

class MainTimer : Fragment()
{
   // private lateinit var binding:
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // super.onCreate(savedInstanceState)
      // binding = FragmentFirstBinding.inflate(layoutInflater)
        //setContentView(binding.root)

       /* binding.startStopButton.setOnClickListener {
            print("hey")
            startStopTimer()
        }
        binding.resetButton.setOnClickListener { resetTimer() }*/


        serviceIntent = Intent(activity?.applicationContext, TimerService::class.java)
        requireActivity().registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
        // registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
    }


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view= inflater.inflate(R.layout.fragment_first, container, false)
            //return inflater.inflate(R.layout.fragment_first, container, false)
            view.findViewById<com.google.android.material.button.MaterialButton>(R.id.startStopButton).setOnClickListener { startStopTimer()}
            view.findViewById<com.google.android.material.button.MaterialButton>(R.id.resetButton).setOnClickListener { resetTimer() }

            return view
        }

    private fun resetTimer()
    {
        stopTimer()
        time = 0.0
        view?.findViewById<TextView>(R.id.timeTV)?.text = getTimeStringFromDouble(time)
    }

    private fun startStopTimer()
    {
        if(timerStarted)
            stopTimer()
        else
            startTimer()
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    private fun startTimer()
    {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        activity?.startService(serviceIntent)
        view?.findViewById<com.google.android.material.button.MaterialButton>(R.id.startStopButton)?.text = "stop"
        //binding.startStopButton.text = "Stop"
        //binding.startStopButton.icon = getDrawable(R.drawable.ic_baseline_pause_24)
        timerStarted = true
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    private fun stopTimer()
    {
       // stopService(serviceIntent)
        activity?.stopService(serviceIntent)
        view?.findViewById<com.google.android.material.button.MaterialButton>(R.id.startStopButton)?.text = "start"
       // binding.startStopButton.text = "Start"
        //binding.startStopButton.icon = getDrawable(R.drawable.ic_baseline_play_arrow_24)
        timerStarted = false
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context, intent: Intent)
        {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            view?.findViewById<TextView>(R.id.timeTV)?.text = getTimeStringFromDouble(time)
           // binding.timeTV.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String
    {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String = String.format("%02d:%02d:%02d", hour, min, sec)
}