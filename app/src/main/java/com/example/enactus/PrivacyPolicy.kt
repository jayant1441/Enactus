package com.example.enactus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_privacy_policy.*

class PrivacyPolicy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        tv_privacy_policy_title.text = intent.extras!!.getString("privacy_policy_title")
        tv_privacy_policy_des.text = intent.extras!!.getString("privacy_policy_des")
    }
}
