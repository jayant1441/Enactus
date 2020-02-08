package com.example.enactus.Fragments

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.enactus.R
import kotlinx.android.synthetic.main.fragment_meditation.*


class FragmentMeditation : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val myfragment_meditation =  inflater.inflate(R.layout.fragment_meditation, container, false)
        return myfragment_meditation
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
    }

    override fun onPause() {
        super.onPause()
        val mp = MediaPlayer.create(context, R.raw.meditation_music)
        mp.stop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val mp = MediaPlayer.create(context, R.raw.meditation_music)
        mp.stop()

    }


}