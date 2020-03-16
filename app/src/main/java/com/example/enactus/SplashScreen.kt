package com.example.enactus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.enactus.IntroSlider.IntroSlider
import com.example.enactus.IntroSliderTextViewUpdate.IntroSliderTV_update_main
import com.example.enactus.LoginSignUp.LoginActivity
import com.example.enactus.LoginSignUp.SignUpActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val mainIntent = Intent(this, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 200)
    }
}

