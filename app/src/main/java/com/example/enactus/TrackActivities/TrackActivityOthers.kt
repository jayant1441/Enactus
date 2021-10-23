package com.example.enactus.TrackActivities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB.RoomDB
import com.example.enactus.R
import kotlinx.android.synthetic.main.activity_track_others.*
import java.time.LocalDate
import java.util.*

class TrackActivityOthers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_others)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_today_date_others.text = LocalDate.now().toString()
        } else {
            tv_today_date_others.text = Date().toString()
        }


        Thread {
            val db = Room.databaseBuilder(this, RoomDB::class.java, "RoomDB")
                .fallbackToDestructiveMigration().build()

            try {
                tv_display_pain_symptoms.text =
                    db.painDao().retrieveData_from_pain_entity()[0].PainSymptomsColumn
                tv_display_fluid_discharge.text =
                    db.FluidDao().retrieveData_from_fluid_entity()[0].FluidColumn
                tv_display_sex_drive.text =
                    db.SexDriveDao().retrieveData_from_SexDrive_entity()[0].SexDriveColumn
            } catch (e: Exception) {
                tv_display_pain_symptoms.text = "No data"
                tv_display_fluid_discharge.text = "No data"
                tv_display_sex_drive.text = "No data"
            }


        }.start()
    }
}
