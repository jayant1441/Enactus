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
import com.example.enactus.PeriodFragmentFunction.DataClass.RV_PainDataClass
import com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB.PainEntity
import com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB.RoomDB
import com.example.enactus.R
import kotlinx.android.synthetic.main.recycler_view_ticket.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class RV_AdapterPain(var context: Context, var listOfPainItems: ArrayList<RV_PainDataClass>) :
    RecyclerView.Adapter<RV_AdapterPain.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recycler_view_ticket, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfPainItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var pain_item = listOfPainItems[position]
        holder.tv_pain_title.text = pain_item.pain_title
        holder.iv_pain_image.setImageResource(pain_item.pain_image)
        holder.iv_pain_image_color.setImageResource(pain_item.pain_image_color)

        holder.constraint_layout_rv_ticket.setOnClickListener {

            Toast.makeText(context, holder.tv_pain_title.text, Toast.LENGTH_SHORT).show()
            holder.iv_pain_image_color.setImageResource(
                R.color.red
            )


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.BASIC_ISO_DATE
                val current_date = current.format(formatter)

                Thread {
                    val db = Room.databaseBuilder(context!!, RoomDB::class.java, "RoomDB")
                        .fallbackToDestructiveMigration().build()
                    try {
                        db.painDao().insert_in_painEntity(
                            PainEntity(
                                current_date,
                                Calendar.MONTH,
                                Calendar.DAY_OF_MONTH,
                                holder.tv_pain_title.text.toString()
                            )
                        )
                    } catch (e: Exception) {

                        Log.d("Exception", e.toString())

                    }

                    db.painDao().retrieveData_from_pain_entity().forEach {
                        Log.i("tag", it.Current_date)
                        Log.i("tag", it.current_month.toString())
                        Log.i("tag", it.current_day.toString())
                        Log.i("tag", it.PainSymptomsColumn)
                        Log.i("tag", Calendar.getInstance().toString())

                    }


                }.start()
            } else {

            }
        }

    }

    inner class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var tv_pain_title = itemview.tv_problem_title
        var iv_pain_image = itemview.iv_physical_param_image
        var iv_pain_image_color = itemview.iv_physical_param_image_color
        var constraint_layout_rv_ticket = itemview.constraint_layout_rv_ticket


    }
}
