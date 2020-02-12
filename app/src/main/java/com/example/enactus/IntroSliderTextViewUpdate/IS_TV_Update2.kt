package com.example.enactus.IntroSliderTextViewUpdate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.enactus.MainActivity
import com.example.enactus.R
import kotlinx.android.synthetic.main.activity_is__tv__update2.*


class IS_TV_Update2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is__tv__update2)


        val IS_weight_pref = getSharedPreferences("IS_weight_pref", Context.MODE_PRIVATE)
        et_picker_weight.setText(IS_weight_pref.getString("IS_Weight" ,""))


        val IS_height_pref = getSharedPreferences("IS_height_pref", Context.MODE_PRIVATE)
        et_picker_height.setText(IS_height_pref.getString("IS_Height" ,""))

        btn_next_IS_Update2.setOnClickListener {

            if(et_picker_weight.text.isEmpty()){
                et_picker_weight.error = "Enter your Weight"
                et_picker_weight.requestFocus()
                return@setOnClickListener
            }

            if (et_picker_height.text.isEmpty()){
                et_picker_height.error = "Enter your Weight"
                et_picker_height.requestFocus()
                return@setOnClickListener
            }

            val IS_weight_pref_editor = IS_weight_pref.edit()
            IS_weight_pref_editor.putString("IS_Weight" , et_picker_weight.text.toString() )
            IS_weight_pref_editor.apply()

            val IS_height_pref_editor = IS_height_pref.edit()
            IS_height_pref_editor.putString("IS_Height" ,et_picker_height.text.toString() )
            IS_height_pref_editor.apply()

            startActivity(Intent(this,IS_TV_Update3::class.java))
            finish()
        }

        btn_back_IS_Update2.setOnClickListener {
            startActivity(Intent(this,IntroSliderTV_update_main::class.java))
            finish()
        }



    }



}
