package com.example.enactus

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.NumberPicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_track_period.*
import java.text.SimpleDateFormat
import java.util.*

class TrackPeriodActivity : AppCompatActivity() {

    var formate = SimpleDateFormat("dd MMM, YYYY",Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_period)

        val IS_period_First_date_pref = getSharedPreferences("IS_period_First_date_pref", Context.MODE_PRIVATE)
        tv_first_day_of_period.text = IS_period_First_date_pref.getString("IS_first_period_date","Select Date")

        var prediction_period_pref = getSharedPreferences("prediction_period_pref", Context.MODE_PRIVATE)
        tv_prediction_next_period.text = prediction_period_pref.getString("prediction_next_period", "Select Date")

        val IS_Duration_period_pref = getSharedPreferences("IS_Duration_period" , Context.MODE_PRIVATE)
        tv_duration_track_period_activity.setText(IS_Duration_period_pref.getString("Duration_key","7"))

        val IS_Recurrence_period_pref = getSharedPreferences("IS_Recurrence_period_pref", Context.MODE_PRIVATE)
        tv_recurrence_track_period_activity.setText(IS_Recurrence_period_pref.getString("Recurrence_key", "120"))

        val predicted_date_pref = getSharedPreferences("predicted_date_pref" , Context.MODE_PRIVATE)
        tv_prediction_next_period.text = predicted_date_pref.getString("predicted_date_pref_key" , "No Date to show")


        var now = Calendar.getInstance()

        tv_first_day_of_period.setOnClickListener {
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val date = formate.format(selectedDate.time)

                tv_first_day_of_period.text = date

//                var first_day_pref = getSharedPreferences("first_day_pref",Context.MODE_PRIVATE)
                var editor = IS_period_First_date_pref.edit()
                editor.putString("IS_first_period_date",tv_first_day_of_period.text.toString())
                editor.apply()
            },
                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()

        }

        tv_prediction_next_period.setOnClickListener {

            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val date = formate.format(selectedDate.time)

                tv_prediction_next_period.text = date

//                var first_day_pref = getSharedPreferences("first_day_pref",Context.MODE_PRIVATE)
                var editor = prediction_period_pref.edit()
                editor.putString("prediction_next_period", tv_prediction_next_period.text.toString())
                editor.apply()
            },
                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }



    }
}
