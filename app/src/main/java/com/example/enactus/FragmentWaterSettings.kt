package com.example.enactus

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fragment_water_settings.*
import java.text.SimpleDateFormat
import java.util.*


class FragmentWaterSettings : AppCompatActivity() {

    private var ins: FragmentWaterSettings? = null
    var now = Calendar.getInstance()
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_water_settings)

        ins = this



        val IS_sleep_time_pref = getSharedPreferences("IS_sleep_time_pref" , Context.MODE_PRIVATE)
        tv_bedtime_time.text = IS_sleep_time_pref.getString("Sleep time", "Select Time")

        val notification_pref = getSharedPreferences("notification_pref" , Context.MODE_PRIVATE)
        tv_wakeup_time.text = notification_pref.getString("wakeUp time","Select time")

        val IS_wakeup_time_pref = getSharedPreferences("IS_wakeup_time_pref" , Context.MODE_PRIVATE)
        tv_wakeup_time.text = IS_wakeup_time_pref.getString("wakeUp time", "Select Time")

        val IS_weight_pref = getSharedPreferences("IS_weight_pref", Context.MODE_PRIVATE)
        tv_Weight_water_settings.text = IS_weight_pref.getString("IS_Weight", "60 Kg") +" Kg"



        tv_wakeup_time.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                tv_wakeup_time.text = timeFormat.format(selectedTime.time)
                StartAlarm(selectedTime)

                val IS_wakeup_time_pref_editor = IS_wakeup_time_pref.edit()
                IS_wakeup_time_pref_editor.putString("wakeUp time" ,tv_wakeup_time.text.toString())
                IS_wakeup_time_pref_editor.apply()

                val notif_editor = notification_pref.edit()
                notif_editor.putString("wakeUp time",tv_wakeup_time.text.toString())
                notif_editor.apply()
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

                val IS_sleep_time_pref_editor = IS_sleep_time_pref.edit()
                IS_sleep_time_pref_editor.putString("Sleep time" ,tv_bedtime_time.text.toString())
                IS_sleep_time_pref_editor.apply()
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePickerDialog .show()
        }



        tv_why_remainder_dont_work.setOnClickListener {
            var intent_to_why_remainder_dont_work = Intent(this, AboutEnactus::class.java)
            intent_to_why_remainder_dont_work.putExtra("intent_title_key" , "Remainder does't work?")
            intent_to_why_remainder_dont_work.putExtra("intent_des_key", "It might be because of following reason:\n\nSometimes cleaner apps or Phone system closed the notification system\n\nSome phone especially Samsung, have strict power plans, We suggest you to add enactus in Unmonitored app list \n\nYou might have cancelled the alarm yourself. Try resetting wakeup time again.  ")
            startActivity(intent_to_why_remainder_dont_work)
        }



        tv_contact_developer.setOnClickListener {
            startActivity(Intent(this,Developer::class.java))
        }



        tv_stop_water_alert.setOnClickListener {
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent  = Intent(this,AlertReciever::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)
            alarmManager.cancel(pendingIntent)
            Toast.makeText(this,"All Alarm Disabled" , Toast.LENGTH_SHORT).show()
        }

    }

    fun getInstace(): FragmentWaterSettings? {
        return ins
    }




    private fun StartAlarm(calender: Calendar) {

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent  = Intent(this,AlertReciever::class.java)
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

    private fun reset_value_at_midnight() {

        var midnight  = Calendar.getInstance()
        midnight.set(Calendar.HOUR_OF_DAY,0)
        midnight.set(Calendar.MINUTE,0)
        midnight.set(Calendar.SECOND,0)
        if(midnight.before(Calendar.getInstance())){
            midnight.add(Calendar.DATE,1)
        }
        var alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, ResetValueMidnight::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,midnight.timeInMillis,pendingIntent)
//        var reset_value_shared_pref = context!!.getSharedPreferences("reset_value_pref", Context.MODE_PRIVATE)
//        var value = reset_value_shared_pref.getString("reset_value","reset failed")
//        tv_water_already_drank.text = value
    }

     fun updateTheTextView( t:String ) {
        this.runOnUiThread(Runnable() {
            fun run() {
                tv_why_remainder_dont_work.setText(t)
            }
        })
    }


}

