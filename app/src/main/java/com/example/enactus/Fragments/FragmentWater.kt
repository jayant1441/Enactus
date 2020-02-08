package com.example.enactus.Fragments

import android.app.AlarmManager
import android.app.AlarmManager.RTC
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.IndianCalendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.enactus.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_fragment_water.*
import kotlinx.android.synthetic.main.fragment_fragment_water.view.*
import java.time.LocalDate
import java.util.*


class FragmentWater : Fragment() {


    companion object {
        var ins: MainActivity? = null
        fun getInstance(): MainActivity? {
            return ins
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ins = MainActivity()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myfragment_water = inflater.inflate(R.layout.fragment_fragment_water, container, false)
        var iv_add_water = myfragment_water.iv_add_water as ImageView
        var tv_water_already_drank = myfragment_water.tv_water_already_drank as TextView
        var tv_water_to_drink_target = myfragment_water.tv_water_to_drink_target as TextView
        val btn_change_water_settings = myfragment_water.btn_change_water_settings as Button
        val iv_water_tips = myfragment_water.iv_water_tips as ImageView

        iv_water_tips.setOnClickListener {
            startActivity(Intent(context, WaterTips::class.java))
        }

        btn_change_water_settings.setOnClickListener {
            startActivity(Intent(context, FragmentWaterSettings::class.java))
        }



        return myfragment_water
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getCurrentDate()

        retrievedata()

        reset_value_at_midnight()




        if (FirebaseAuth.getInstance().currentUser != null) {
            iv_add_water.setOnClickListener {
                var waterDrank = tv_water_already_drank.text.toString().toInt()
                var water_drank_new = waterDrank + 300

                if (water_drank_new >= 3100) {
                    tv_water_already_drank.text = water_drank_new.toString()
                    Toast.makeText(
                        context,
                        "Congratulations on completing the target.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    tv_water_already_drank.text = water_drank_new.toString()
                }
                savedata()
            }
        } else {
            iv_add_water.setOnClickListener {
                Toast.makeText(context, "Login first", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun savedata() {
        val mypref = this.getActivity()!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = mypref.edit()
        editor.putString("waterdrank", tv_water_already_drank.text.toString())
        editor.apply()
    }

    private fun retrievedata() {
        val mypref = this.getActivity()!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val newest_water = mypref.getString("waterdrank", "0")
        tv_water_already_drank.text = newest_water
    }

    private fun getCurrentDate() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var current_date = LocalDate.now()
            tv_current_date.text = current_date.toString()
        } else {
            var current_date = Date()
            tv_current_date.text = current_date.toString()
        }
    }

    private fun reset_value_at_midnight() {
        var midnight  = Calendar.getInstance()
        midnight.set(Calendar.HOUR_OF_DAY,0)
        midnight.set(Calendar.MINUTE,0)
        if(midnight.before(Calendar.getInstance())){
            midnight.add(Calendar.DATE,1)
        }
        var alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(context, ResetValueMidnight::class.java)
//        intent.action = Intent.ACTION_DATE_CHANGED
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,midnight.timeInMillis,pendingIntent)




//        var reset_value_shared_pref = context!!.getSharedPreferences("reset_value_pref", Context.MODE_PRIVATE)
//        var value = reset_value_shared_pref.getString("reset_value","reset failed")
//        tv_water_already_drank.text = value
    }










}