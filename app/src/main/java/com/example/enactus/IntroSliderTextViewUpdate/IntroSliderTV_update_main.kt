package com.example.enactus.IntroSliderTextViewUpdate

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.enactus.MainActivity
import com.example.enactus.R
import kotlinx.android.synthetic.main.activity_intro_slider_tv_update_main.*

class IntroSliderTV_update_main : AppCompatActivity() {


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_slider_tv_update_main)


        val intro_TV_update_pref =
            getSharedPreferences("intro_TV_update_pref", Context.MODE_PRIVATE)

        if (!intro_TV_update_pref.getBoolean("put_show_boolean", true)) {
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }

        val intro_TV_update_pref_editor = intro_TV_update_pref.edit()
        intro_TV_update_pref_editor.putBoolean("put_show_boolean", false)
        intro_TV_update_pref_editor.apply()


        btn_lets_go.setOnClickListener {
            startActivity(Intent(this, IS_TV_Update2::class.java))
        }
    }


}
