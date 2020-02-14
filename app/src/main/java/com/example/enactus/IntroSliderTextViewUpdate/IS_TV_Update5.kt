package com.example.enactus.IntroSliderTextViewUpdate

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.enactus.MainActivity
import com.example.enactus.R
import kotlinx.android.synthetic.main.activity_is_tv_update5.*
import java.text.SimpleDateFormat
import java.util.*

class IS_TV_Update5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is_tv_update5)

        val now = Calendar.getInstance()
        var dateformat = SimpleDateFormat("yyyy-MM-dd", Locale.US)


        val IS_period_First_date_pref = getSharedPreferences("IS_period_First_date_pref", Context.MODE_PRIVATE)
        tv_IS_period_First_date.text = IS_period_First_date_pref.getString("IS_first_period_date", "Select Date")

        val IS_Duration_period_pref = getSharedPreferences("IS_Duration_period" , Context.MODE_PRIVATE)
        et_picker_duration.setText(IS_Duration_period_pref.getString("Duration_key",""))

        val IS_Recurrence_period_pref = getSharedPreferences("IS_Recurrence_period_pref", Context.MODE_PRIVATE)
        et_picker_recurrence.setText(IS_Recurrence_period_pref.getString("Recurrence_key", ""))



        btn_next_IS_Update5.setOnClickListener {

            val IS_Duration_period_pref_editor = IS_Duration_period_pref.edit()
            IS_Duration_period_pref_editor.putString("Duration_key" , et_picker_duration.text.toString())
            IS_Duration_period_pref_editor.apply()

            var IS_Recurrence_period_pref_editor = IS_Recurrence_period_pref.edit()
            IS_Recurrence_period_pref_editor.putString("Recurrence_key" , et_picker_recurrence.text.toString())
            IS_Recurrence_period_pref_editor.apply()


            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }



        btn_back_IS_Update5.setOnClickListener {
            startActivity(Intent(this,IS_TV_Update4::class.java))
            finish()
        }




        tv_IS_period_First_date.setOnClickListener {
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val date = dateformat.format(selectedDate.time)


                tv_IS_period_First_date.text = date
                var IS_period_First_date_pref_editor = IS_period_First_date_pref.edit()
                IS_period_First_date_pref_editor.putString("IS_first_period_date" , tv_IS_period_First_date.text.toString())
                IS_period_First_date_pref_editor.apply()
//                var prediction_period_pref = context!!.getSharedPreferences("first_day_pref", Context.MODE_PRIVATE)
//                var editor = prediction_period_pref.edit()
//                editor.putString("prediction_next_period", tv_next_period.text.toString())
//                editor.apply()
            },
                now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()

        }



    }
}
