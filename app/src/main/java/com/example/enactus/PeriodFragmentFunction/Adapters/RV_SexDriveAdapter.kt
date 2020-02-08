package com.example.enactus.PeriodFragmentFunction.Adapters

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.enactus.PeriodFragmentFunction.DataClass.RV_SexDataClass
import com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB.RoomDB
import com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB.SexDriveEntity
import com.example.enactus.R
import kotlinx.android.synthetic.main.recycler_view_ticket.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class RV_SexDriveAdapter(var context: Context, var listOfSexDriveItems:ArrayList<RV_SexDataClass>): RecyclerView.Adapter<RV_SexDriveAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_ticket,parent,false))
    }

    override fun getItemCount(): Int {
        return listOfSexDriveItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var sexDrive_item = listOfSexDriveItems[position]
        holder.tv_sex_drive_title.text = sexDrive_item.Sex_drive_title
        holder.iv_sex_drive_image.setImageResource(sexDrive_item.sex_drive_image)
        holder.iv_sex_drive_image_color.setImageResource(sexDrive_item.sex_drive_image_color)

        holder.constraint_layout_rv_ticket.setOnClickListener {

            Toast.makeText(context, holder.tv_sex_drive_title.text, Toast.LENGTH_SHORT).show()
            holder.iv_sex_drive_image_color.setImageResource(R.color.red
            )


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.BASIC_ISO_DATE
                val current_date = current.format(formatter)

                Thread {
                    val db = Room.databaseBuilder(context!!, RoomDB::class.java, "RoomDB").fallbackToDestructiveMigration().build()
                    try {
                        db.SexDriveDao().insert_in_SexDriveEntity(
                            SexDriveEntity(
                                current_date,
                                Calendar.DAY_OF_MONTH,
                                Calendar.DAY_OF_MONTH,
                                holder.tv_sex_drive_title.text.toString())
                        )


                    } catch (e: Exception) {

                        Log.d("Exception", e.toString())

                    }

                    db.SexDriveDao().retrieveData_from_SexDrive_entity().forEach{
                        Log.i("tag",it.Current_date)
                        Log.i("tag",it.current_month.toString())
                        Log.i("tag",it.current_day.toString())
                        Log.i("tag",it.SexDriveColumn)
                        Log.i("tag", Calendar.getInstance().toString())

                    }


                }.start()
            }
            else{

            }
        }

    }

    inner class MyViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var tv_sex_drive_title = itemview.tv_problem_title
        var iv_sex_drive_image = itemview.iv_physical_param_image
        var iv_sex_drive_image_color = itemview.iv_physical_param_image_color
        var constraint_layout_rv_ticket = itemview.constraint_layout_rv_ticket


    }
}
