package dat257.gyro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class WalkFragment : Fragment() {

    private lateinit var walkMinimize: Button
    private lateinit var walkPlay: Button
    private lateinit var walkReset: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=  inflater.inflate(R.layout.fragment_walk, container, false)
        walkMinimize = view.findViewById(R.id.walkMinimize)
        walkMinimize.setBackgroundResource(R.drawable.ic_baseline_minimize_24)
        walkPlay = view.findViewById(R.id.startStopButton)
        walkPlay.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
        walkReset = view.findViewById(R.id.resetButton)
        walkReset.setBackgroundResource(R.drawable.ic_baseline_refresh_24)
        return view
    }


}