package com.example.enactus.TrackActivities

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.enactus.R
import com.example.enactus.WaterDatabase.WaterDB
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_track_water.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TrackWaterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_water)

        val mypref = this.getSharedPreferences("pref", Context.MODE_PRIVATE)
        tv_water_drank_today.text = mypref.getString("waterdrank", "0 ml")


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_today_date.text = LocalDate.now().toString()
        } else {
            tv_today_date.text = Date().toString()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.BASIC_ISO_DATE
            val current_date = current.format(formatter)
            val current_month = current_date.substring(4, 6).toInt()

            tv_month_name.text = when (current_month) {
                1 -> "January"
                2 -> "February"
                3 -> "March"
                4 -> "April"
                5 -> "May"
                6 -> "June"
                7 -> "July"
                8 -> "August"
                9 -> "September"
                10 -> "October"
                11 -> "November"
                12 -> "December"

                else -> "No date to display"
            }
        }











        Thread {
            try {
                retrieve_data()

            } catch (e: Exception) {
                Log.d("show_list", "e")

            }
        }.start()


    }

    private fun retrieve_data(): ArrayList<BarEntry> {
        val water_db = Room.databaseBuilder(this, WaterDB::class.java, "WaterDB").build()

        val data_vals = ArrayList<BarEntry>()
        val x_axis_value = ArrayList<BarEntry>()
        var i = 1
        while (i < water_db.WaterDAO().retrieve_data_from_Water_entity().count()) {
            x_axis_value.add(
                BarEntry(
                    water_db.WaterDAO().retrieve_data_from_Water_entity()[i].day_of_month.toFloat(),
                    water_db.WaterDAO()
                        .retrieve_data_from_Water_entity()[i].water_drink_today.toFloat()
                )
            )
            i = i + 1

        }

        var bar_data_set = BarDataSet(x_axis_value, "x-axis")

        bar_data_set.barBorderWidth = 1f


        var data = BarData(bar_data_set)
        data.barWidth = 0.1f

        bar_graph_water.data = data
        bar_graph_water.description.isEnabled = false
        return data_vals

    }

}

