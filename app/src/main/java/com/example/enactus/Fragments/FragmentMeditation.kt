package com.example.enactus.Fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.example.enactus.R
import kotlinx.android.synthetic.main.fragment_meditation.view.*


class FragmentMeditation : Fragment() {

    var isStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState!=null && savedInstanceState.containsKey("media_player_state")){
            isStarted = savedInstanceState.getBoolean("media_player_state");
        }
        else {
            isStarted = false
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val myfragment_meditation =  inflater.inflate(R.layout.fragment_meditation, container, false)

        val btn_pause_play = myfragment_meditation.btn_pause_play as ToggleButton

        val mp = MediaPlayer.create(context, R.raw.meditation_music)
        mp.isLooping = true


        btn_pause_play.setOnCheckedChangeListener { buttonView, isChecked ->
            if (btn_pause_play.isChecked) {
                btn_pause_play.setBackgroundResource(R.drawable.pause)
                mp.start()
            } else {
                btn_pause_play.setBackgroundResource(R.drawable.play)
                mp.pause()
            }

        }

        return myfragment_meditation
    }


    override fun onPause() {
        super.onPause()
        val mp = MediaPlayer.create(context, R.raw.meditation_music)
        mp.pause()
    }

    override fun onDetach() {
        super.onDetach()
        val mp = MediaPlayer.create(context, R.raw.meditation_music)
        mp.pause()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        val mp = MediaPlayer.create(context, R.raw.meditation_music)
        mp.pause()

    }

    override fun onDestroy() {
        super.onDestroy()
        val mp = MediaPlayer.create(context, R.raw.meditation_music)
        mp.pause()

    }

     override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("media_player_state", true)
        super.onSaveInstanceState(outState)
    }


}
