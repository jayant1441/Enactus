package com.example.enactus.IntroSlider

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.enactus.IntroSliderTextViewUpdate.IntroSliderTV_update_main
import com.example.enactus.MainActivity
import com.example.enactus.R
import kotlinx.android.synthetic.main.activity_intro_slider.*



class IntroSlider : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_slider)

        val sharedpref_intro_slider = getSharedPreferences("intro_slider_pref" , Context.MODE_PRIVATE)

        if (!sharedpref_intro_slider.getBoolean("put_show_boolean", true)){
            startActivity(Intent(baseContext, IntroSliderTV_update_main::class.java))
            finish()
        }

        view_pager2.adapter = IntroSliderAdapter()

        view_pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            @SuppressLint("NewApi")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when(position){
                    0 -> {
                        indicator1.setTextColor(getColor(R.color.black))
                        indicator2.setTextColor(getColor(R.color.darker_gray))
                        indicator3.setTextColor(getColor(R.color.darker_gray))
                        btn_next.setTextColor(getColor(R.color.white))
                    }

                    1 -> {
                        indicator1.setTextColor(getColor(R.color.darker_gray))
                        indicator2.setTextColor(getColor(R.color.black))
                        indicator3.setTextColor(getColor(R.color.darker_gray))
                        btn_next.setTextColor(getColor(R.color.white))
                    }

                    2-> {
                        indicator1.setTextColor(getColor(R.color.darker_gray))
                        indicator2.setTextColor(getColor(R.color.darker_gray))
                        indicator3.setTextColor(getColor(R.color.black))
                        btn_next.setTextColor(getColor(R.color.black))

                        btn_next.setOnClickListener {

                            startActivity(Intent(baseContext, IntroSliderTV_update_main::class.java))
                            finish()

                            val intro_pref_editor = sharedpref_intro_slider.edit()
                            intro_pref_editor.putBoolean("put_show_boolean" , false)
                            intro_pref_editor.apply()
                        }
                    }
                }
            }
        })


        btn_skip.setOnClickListener {
            startActivity(Intent(baseContext, IntroSliderTV_update_main::class.java))
            finish()
        }

    }

}
