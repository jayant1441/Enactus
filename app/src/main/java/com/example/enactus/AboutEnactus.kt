package com.example.enactus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about_enactus.*

class AboutEnactus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_enactus)

        tv_title_intent.text = intent.extras!!.getString("intent_title_key")
        tv_des_intent.text = intent.extras!!.getString("intent_des_key")

        tv_title_intent.text = intent.extras!!.getString("about_pcod")
        tv_des_intent.text = intent.extras!!.getString("about_des")


    }
}
