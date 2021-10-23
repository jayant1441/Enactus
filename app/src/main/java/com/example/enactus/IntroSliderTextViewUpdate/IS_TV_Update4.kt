package com.example.enactus.IntroSliderTextViewUpdate

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.enactus.R
import com.example.enactus.SleepTimeDataClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_is__tv__update4.*
import java.text.SimpleDateFormat
import java.util.*

class IS_TV_Update4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is__tv__update4)

        val current_user_uid = FirebaseAuth.getInstance().currentUser!!.uid


        val now = Calendar.getInstance()
        var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

//        val IS_sleep_time_pref = getSharedPreferences("IS_sleep_time_pref" , Context.MODE_PRIVATE)
//        tv_IS_sleep_time.text = IS_sleep_time_pref.getString("Sleep time", "Select Time")


        btn_next_IS_Update4.setOnClickListener {
            if (tv_IS_sleep_time.text.equals("Select Time")) {
                Toast.makeText(this, "Please Select Sleep time", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, IS_TV_Update5::class.java))
                finish()
            }

        }

        btn_back_IS_Update4.setOnClickListener {
            startActivity(Intent(this, IS_TV_Update3::class.java))
            finish()
        }


        tv_IS_sleep_time.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    val selectedTime = Calendar.getInstance()
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set(Calendar.MINUTE, minute)
                    tv_IS_sleep_time.text = timeFormat.format(selectedTime.time)
//                StartAlarm(selectedTime)

//                val IS_sleep_time_pref_editor = IS_sleep_time_pref.edit()
//                IS_sleep_time_pref_editor.putString("Sleep time" ,tv_IS_sleep_time.text.toString())
//                IS_sleep_time_pref_editor.apply()

                    val ref =
                        FirebaseDatabase.getInstance().getReference("/all-data/$current_user_uid")
                    ref.child("sleep-data")
                        .setValue(SleepTimeDataClass(tv_IS_sleep_time.text.toString()))
                },
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
            )
            timePickerDialog.show()
        }
    }
}
