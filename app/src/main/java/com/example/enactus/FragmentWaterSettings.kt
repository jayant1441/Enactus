package com.example.enactus

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fragment_water_settings.*
import java.text.SimpleDateFormat
import java.util.*


class FragmentWaterSettings : AppCompatActivity() {

    var now = Calendar.getInstance()
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_water_settings)


        tv_wakeup_time.setOnClickListener {

            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                tv_wakeup_time.text = timeFormat.format(selectedTime.time)
                StartAlarm(selectedTime)
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePickerDialog .show()
        }

        tv_bedtime_time.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                tv_bedtime_time.text = timeFormat.format(selectedTime.time)
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePickerDialog .show()
        }

    }

    private fun StartAlarm(calender: Calendar) {

        var alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent  = Intent(this,AlertReciever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)
        if (calender.before(Calendar.getInstance())) {
            calender.add(Calendar.DATE, 1)
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calender.timeInMillis,pendingIntent)
        val long : Long = 60*60*1000
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calender.timeInMillis ,long,pendingIntent)





    }


}
