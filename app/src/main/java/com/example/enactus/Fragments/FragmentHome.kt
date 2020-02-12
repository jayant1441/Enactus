package com.example.enactus.Fragments


import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.enactus.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*


class FragmentHome : Fragment() {


    var formate = SimpleDateFormat("dd MMM, YYYY",Locale.US)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)
    val now = Calendar.getInstance()






    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val myfragmenthome =  inflater.inflate(R.layout.fragment_home, container, false)


        return myfragmenthome
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val IS_weight_pref = context!!.getSharedPreferences("IS_weight_pref", Context.MODE_PRIVATE)
        tv_weight.text = IS_weight_pref.getString("IS_Weight" ,"60") + " Kg"

        val IS_height_pref = context!!.getSharedPreferences("IS_height_pref", Context.MODE_PRIVATE)
        tv_height.text = IS_height_pref.getString("IS_Height" ,"155") + " Cm"

        val IS_wakeup_time_pref = context!!.getSharedPreferences("IS_wakeup_time_pref" , Context.MODE_PRIVATE)
        tv_wakeup.text = IS_wakeup_time_pref.getString("wakeUp time", "6:30 AM")

        val IS_period_First_date_pref = context!!.getSharedPreferences("IS_period_First_date_pref", Context.MODE_PRIVATE)
        tv_first_period.text = IS_period_First_date_pref.getString("IS_first_period_date","Select Date")



        cv_track_activity_water.setOnClickListener {
            startActivity(Intent(context, TrackWaterActivity::class.java))
        }

        cv_track_activity_others.setOnClickListener {
            startActivity(Intent(context,OtherGraph::class.java))
        }

        cv_track_activity_period.setOnClickListener {
            startActivity(Intent(context,TrackPeriodActivity::class.java))
        }





        tv_next_period.setOnClickListener {

//            val now = Calendar.getInstance()
//            val datePicker = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                val selectedDate = Calendar.getInstance()
//                selectedDate.set(Calendar.YEAR,year)
//                selectedDate.set(Calendar.MONTH,month)
//                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
//                val date = formate.format(selectedDate.time)
//
//                tv_next_period.text = date
//
////                var prediction_period_pref = context!!.getSharedPreferences("first_day_pref", Context.MODE_PRIVATE)
//                var editor = prediction_period_pref.edit()
//                editor.putString("prediction_next_period", tv_next_period.text.toString())
//                editor.apply()
//            },
//                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH)
//            )
//            datePicker.show()
        }

        tv_wakeup.setOnClickListener {
//
            val timePickerDialog = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                tv_wakeup.text = timeFormat.format(selectedTime.time)
                StartAlarm(selectedTime)
//
//                shared_pref("wakeUp time", tv_wakeup.text.toString())
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePickerDialog .show()
        }
    }






    private fun StartAlarm(calender: Calendar) {

        var alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent  = Intent(context, AlertReciever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context,0,intent,0)
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