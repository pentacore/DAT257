package dat257.gyro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import dat257.gyro.model.RecordingControllerInstruction
import dat257.gyro.patterns.publisherSubscriber.ChannelName
import dat257.gyro.patterns.publisherSubscriber.Publisher

class WalkFragment : Fragment(), Publisher {

  //  private lateinit var walkMinimize: Button
    private lateinit var walkPlay: Button
    private lateinit var walkReset: Button
    private var isStarted  = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=  inflater.inflate(R.layout.fragment_walk, container, false)
       // walkMinimize = view.findViewById(R.id.walkMinimize)
       // walkMinimize.setBackgroundResource(R.drawable.ic_baseline_minimize_24)
        walkPlay = view.findViewById(R.id.startStopButton)
        walkPlay.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
        walkReset = view.findViewById(R.id.resetButton)
        walkReset.setBackgroundResource(R.drawable.ic_baseline_refresh_24)

        walkPlay.setOnClickListener {
            childFragmentManager.beginTransaction().apply {
                Log.i("yaay", "snel hest")
                isStarted= !isStarted
                // walkButton.setBa
                if(isStarted){
                    walkPlay.setBackgroundResource(R.drawable.ic_baseline_pause_24)
                    publish(ChannelName.RecordingControl, RecordingControllerInstruction.Play as Message)
                }else{
                    walkPlay.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)

                }

                commit()
            }

        }
        return view

    }





}