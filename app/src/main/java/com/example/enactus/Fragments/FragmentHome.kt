package com.example.enactus.Fragments


import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.enactus.*
import kotlinx.android.synthetic.main.activity_track_period.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*


class FragmentHome : Fragment() {


    var formate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
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

        val IS_Recurrence_period_pref = context!!.getSharedPreferences("IS_Recurrence_period_pref", Context.MODE_PRIVATE)
        val rec = (IS_Recurrence_period_pref.getString("Recurrence_key", "120")).toString().toInt()

        val predicted_date_pref = context!!.getSharedPreferences("predicted_date_pref" , Context.MODE_PRIVATE)
        tv_predicted_next_period_hf.text = predicted_date_pref.getString("predicted_date_pref_key" , "No Date to show")


        cv_track_activity_water.setOnClickListener {
            startActivity(Intent(context, TrackWaterActivity::class.java))
        }

        cv_track_activity_others.setOnClickListener {
            startActivity(Intent(context,TrackActivityOthers::class.java))
        }

        cv_track_activity_period.setOnClickListener {
            startActivity(Intent(context,TrackPeriodActivity::class.java))
        }


        var dateInString = IS_period_First_date_pref.getString("IS_first_period_date","2020-11-20")
        var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val c = Calendar.getInstance()
        c.time = sdf.parse(dateInString)
        c.add(Calendar.DATE, rec.toInt())
        sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val resultdate = Date(c.timeInMillis)
        dateInString = sdf.format(resultdate)
        tv_predicted_next_period_hf.text = dateInString

        val predicted_date_pref_editor =  predicted_date_pref.edit()
        predicted_date_pref_editor.putString("predicted_date_pref_key" , tv_predicted_next_period_hf.text.toString())
        predicted_date_pref_editor.apply()



        tv_first_period.setOnClickListener {

            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val date = formate.format(selectedDate.time)

                tv_first_period.text = date

                var IS_period_First_date_pref_editor = IS_period_First_date_pref.edit()
                IS_period_First_date_pref_editor.putString("IS_first_period_date" , tv_first_period.text.toString())
                IS_period_First_date_pref_editor.apply()
            },
                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
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
    }





}