package com.example.enactus.Fragments


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.enactus.*
import com.example.enactus.IntroSliderTextViewUpdate.IS_TV_Update5
import com.example.enactus.R
import com.example.enactus.TrackActivities.TrackActivityOthers
import com.example.enactus.TrackActivities.TrackPeriodActivity
import com.example.enactus.TrackActivities.TrackWaterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*


class FragmentHome : Fragment() {


    var formate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)
    val now = Calendar.getInstance()

    val current_user_uid = FirebaseAuth.getInstance().currentUser!!.uid
    val databaseReference = FirebaseDatabase.getInstance().getReference("all-data/${current_user_uid}")

    lateinit var tv_wakeup: TextView
    lateinit var tv_first_period: TextView
    lateinit var tv_predicted_next_period_hf:TextView
    lateinit var tv_weight:TextView
    lateinit var tv_height :TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val myfragmenthome = inflater.inflate(R.layout.fragment_home, container, false)

        tv_wakeup = myfragmenthome.findViewById(R.id.tv_wakeup)
        tv_first_period = myfragmenthome.findViewById(R.id.tv_first_period)
        tv_predicted_next_period_hf = myfragmenthome.findViewById(R.id.tv_predicted_next_period_hf)
        tv_weight = myfragmenthome.findViewById(R.id.tv_weight)
        tv_height = myfragmenthome.findViewById(R.id.tv_height)



        return myfragmenthome
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val weight_height_ref = databaseReference.child("weight-height-data")
        weight_height_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val data_weight_height = p0.getValue(HeightWeightDataClass::class.java)
                tv_weight.text = data_weight_height!!.weight + " Kg"
                tv_height.text = data_weight_height!!.height + " cm"
            }

        })

        val wakeup_data_ref = databaseReference.child("wakeup-data")
        wakeup_data_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val wakeUpTime = p0.getValue(wakeUpTimeDataClass::class.java)
                if (wakeUpTime!=null){
                    tv_wakeup.text = wakeUpTime!!.wakeUpTime
                }
                else{
                    return
                }
            }

        })


        val others_data_ref = databaseReference.child("others-data")
        others_data_ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val updateLastdata = p0.getValue(update_lastDataClass::class.java)
                if (updateLastdata != null) {
                    tv_first_period.text = updateLastdata.firstDayOFPeriod
                    val recurrence = updateLastdata.recurrence

                    if (recurrence != null) {
                        if (!tv_first_period.equals("Select Date")){
                            var dateInString = tv_first_period.text.toString()
                            var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                            val c = Calendar.getInstance()
                            c.time = sdf.parse(dateInString)
                            c.add(Calendar.DATE, recurrence.toInt())
                            sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                            val resultdate = Date(c.timeInMillis)
                            dateInString = sdf.format(resultdate)
                            tv_predicted_next_period_hf.text = dateInString
                        }

                    }

                }

            }

        })







        cv_track_activity_water.setOnClickListener {
            startActivity(Intent(context, TrackWaterActivity::class.java))
        }

        cv_track_activity_others.setOnClickListener {
            startActivity(Intent(context, TrackActivityOthers::class.java))
        }

        cv_track_activity_period.setOnClickListener {
            startActivity(Intent(context, TrackPeriodActivity::class.java))
        }


//        val predicted_date_pref_editor =  predicted_date_pref.edit()
//        predicted_date_pref_editor.putString("predicted_date_pref_key" , tv_predicted_next_period_hf.text.toString())
//        predicted_date_pref_editor.apply()


        tv_first_period.setOnClickListener {
            startActivity(Intent(context,IS_TV_Update5::class.java))
            Toast.makeText(context, "You can change settings here", Toast.LENGTH_SHORT).show()
        }

        tv_predicted_next_period_hf.setOnClickListener {
            Toast.makeText(context, "This is the predicted date for your period", Toast.LENGTH_SHORT).show()

        }

    }
}