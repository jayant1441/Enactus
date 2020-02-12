package com.example.enactus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_track_water.*
import java.util.ArrayList

class TrackWaterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_water)

        val x_axis_value = ArrayList<BarEntry>()
        x_axis_value.add(BarEntry(10f,1f))
        x_axis_value.add(BarEntry(20f,2f))
        x_axis_value.add(BarEntry(30f,3f))
        x_axis_value.add(BarEntry(40f,4f))

        var bar_data_set = BarDataSet(x_axis_value,"x-axis")

        var dates = ArrayList<String>()
        dates.add("hi")
        dates.add("hello")
        dates.add("yo")

        var data = BarData(bar_data_set)
        bar_graph_water.data = data

    }
}
