package com.example.enactus.IntroSliderTextViewUpdate

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.enactus.AlertReciever
import com.example.enactus.R
import kotlinx.android.synthetic.main.activity_is_tv_update3.*
import java.text.SimpleDateFormat
import java.util.*

class IS_TV_Update3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is_tv_update3)

        val now = Calendar.getInstance()
        var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

        val IS_wakeup_time_pref = getSharedPreferences("IS_wakeup_time_pref" , Context.MODE_PRIVATE)
        tv_IS_wakeup_time.text = IS_wakeup_time_pref.getString("wakeUp time", "Select Time")


        btn_next_IS_Update3.setOnClickListener {
            if (tv_IS_wakeup_time.text.equals("Select Time")){
                Toast.makeText(this, "Please Select Wakeup time", Toast.LENGTH_SHORT).show()
            }
            else{
                startActivity(Intent(this,IS_TV_Update4::class.java))
                finish()
            }

        }

        btn_back_IS_Update3.setOnClickListener {
            startActivity(Intent(this,IS_TV_Update2::class.java))
            finish()
        }


        tv_IS_wakeup_time.setOnClickListener {

            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                tv_IS_wakeup_time.text = timeFormat.format(selectedTime.time)
                StartAlarm(selectedTime)

                val IS_wakeup_time_pref_editor = IS_wakeup_time_pref.edit()
                IS_wakeup_time_pref_editor.putString("wakeUp time" ,tv_IS_wakeup_time.text.toString())
                IS_wakeup_time_pref_editor.apply()

            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePickerDialog .show()
        }
    }

    private fun StartAlarm(calender: Calendar) {

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent  = Intent(this, AlertReciever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)
        if (calender.before(Calendar.getInstance())) {
            calender.add(Calendar.DATE, 1)
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calender.timeInMillis,pendingIntent)
        val long : Long = 1000*60*60

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calender.timeInMillis ,long,pendingIntent)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,long,1000*60*60,pendingIntent)
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,2*long,3*long,pendingIntent)
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,3*long,4*long,pendingIntent)
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,4*long,5*long,pendingIntent)
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,5*long,6*long,pendingIntent)
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,6*long,7*long,pendingIntent)
    }
}
