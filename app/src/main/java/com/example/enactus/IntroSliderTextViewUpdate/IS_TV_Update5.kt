package com.example.enactus.IntroSliderTextViewUpdate

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.enactus.MainActivity
import com.example.enactus.R
import com.example.enactus.update_lastDataClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_is_tv_update5.*
import java.text.SimpleDateFormat
import java.util.*

class IS_TV_Update5 : AppCompatActivity() {

    var first_date_of_period: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_is_tv_update5)

        val current_user_uid = FirebaseAuth.getInstance().currentUser!!.uid


        val now = Calendar.getInstance()
        var dateformat = SimpleDateFormat("yyyy-MM-dd", Locale.US)


//        val IS_period_First_date_pref = getSharedPreferences("IS_period_First_date_pref", Context.MODE_PRIVATE)
//        tv_IS_period_First_date.text = IS_period_First_date_pref.getString("IS_first_period_date", "Select Date")
//
//        val IS_Duration_period_pref = getSharedPreferences("IS_Duration_period" , Context.MODE_PRIVATE)
//        et_picker_duration.setText(IS_Duration_period_pref.getString("Duration_key",""))
//
//        val IS_Recurrence_period_pref = getSharedPreferences("IS_Recurrence_period_pref", Context.MODE_PRIVATE)
//        et_picker_recurrence.setText(IS_Recurrence_period_pref.getString("Recurrence_key", ""))


        btn_next_IS_Update5.setOnClickListener {

            if (tv_IS_period_First_date.text.equals("Select Date") || et_picker_duration.text.isEmpty() || et_picker_recurrence.text.isEmpty()) {
                Toast.makeText(this, "Please fill al fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val ref = FirebaseDatabase.getInstance().getReference("/all-data/$current_user_uid")
            ref.child("others-data").setValue(
                update_lastDataClass(
                    first_date_of_period,
                    et_picker_duration.text.toString(),
                    et_picker_recurrence.text.toString()
                )
            )

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }



        btn_back_IS_Update5.setOnClickListener {
            startActivity(Intent(this, IS_TV_Update4::class.java))
            finish()
        }




        tv_IS_period_First_date.setOnClickListener {
            val datePicker = DatePickerDialog(
                this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(Calendar.YEAR, year)
                    selectedDate.set(Calendar.MONTH, month)
                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = dateformat.format(selectedDate.time)

                    tv_IS_period_First_date.text = date

                    first_date_of_period = tv_IS_period_First_date.text.toString()

//                var IS_period_First_date_pref_editor = IS_period_First_date_pref.edit()
//                IS_period_First_date_pref_editor.putString("IS_first_period_date" , tv_IS_period_First_date.text.toString())
//                IS_period_First_date_pref_editor.apply()
//                var prediction_period_pref = context!!.getSharedPreferences("first_day_pref", Context.MODE_PRIVATE)
//                var editor = prediction_period_pref.edit()
//                editor.putString("prediction_next_period", tv_next_period.text.toString())
//                editor.apply()
                },
                now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()

        }


    }
}
