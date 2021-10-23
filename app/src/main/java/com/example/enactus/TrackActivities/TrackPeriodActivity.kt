package com.example.enactus.TrackActivities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.enactus.R
import com.example.enactus.update_lastDataClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_track_period.*
import java.text.SimpleDateFormat
import java.util.*

class TrackPeriodActivity : AppCompatActivity() {

    var formate = SimpleDateFormat("dd MMM, YYYY", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_period)

        val current_user_uid = FirebaseAuth.getInstance().currentUser!!.uid
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("all-data/${current_user_uid}")


//        val IS_period_First_date_pref = getSharedPreferences("IS_period_First_date_pref", Context.MODE_PRIVATE)
//        tv_first_day_of_period.text = IS_period_First_date_pref.getString("IS_first_period_date","Select Date")
//
//        var prediction_period_pref = getSharedPreferences("prediction_period_pref", Context.MODE_PRIVATE)
//        tv_prediction_next_period.text = prediction_period_pref.getString("prediction_next_period", "Select Date")
//
//        val IS_Duration_period_pref = getSharedPreferences("IS_Duration_period" , Context.MODE_PRIVATE)
//        tv_duration_track_period_activity.setText(IS_Duration_period_pref.getString("Duration_key","7"))
//
//        val IS_Recurrence_period_pref = getSharedPreferences("IS_Recurrence_period_pref", Context.MODE_PRIVATE)
//        tv_recurrence_track_period_activity.setText(IS_Recurrence_period_pref.getString("Recurrence_key", "120"))
//
//        val predicted_date_pref = getSharedPreferences("predicted_date_pref" , Context.MODE_PRIVATE)
//        tv_prediction_next_period.text = predicted_date_pref.getString("predicted_date_pref_key" , "No Date to show")


        var now = Calendar.getInstance()

        tv_first_day_of_period.setOnClickListener {
            Toast.makeText(this, "To change setting go to settings tab", Toast.LENGTH_SHORT).show()
        }

        tv_prediction_next_period.setOnClickListener {
            Toast.makeText(this, "To change setting go to settings tab", Toast.LENGTH_SHORT).show()
        }


        val others_data_ref = databaseReference.child("others-data")
        others_data_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val updateLastdata = p0.getValue(update_lastDataClass::class.java)
                if (updateLastdata != null) {

                    tv_first_day_of_period.text = updateLastdata.firstDayOFPeriod
                    val recurrence = updateLastdata.recurrence
                    tv_duration_track_period_activity.text = updateLastdata.duration_of_period
                    tv_recurrence_track_period_activity.text = updateLastdata.recurrence

                    if (recurrence != null) {
                        var dateInString = tv_first_day_of_period.text.toString()
                        var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                        val c = Calendar.getInstance()
                        c.time = sdf.parse(dateInString)
                        c.add(Calendar.DATE, recurrence.toInt())
                        sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                        val resultdate = Date(c.timeInMillis)
                        dateInString = sdf.format(resultdate)
                        tv_prediction_next_period.text = dateInString
                    }


                }

            }

        })


    }
}
