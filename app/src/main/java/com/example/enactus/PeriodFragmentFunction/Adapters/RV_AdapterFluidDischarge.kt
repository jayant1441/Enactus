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
import com.example.enactus.PeriodFragmentFunction.DataClass.RV_FluidDataClass
import com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB.FluidEntity
import com.example.enactus.PeriodFragmentFunction.PeriodFragmentRoomDB.RoomDB
import com.example.enactus.R
import kotlinx.android.synthetic.main.recycler_view_ticket.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class RV_AdapterFluidDischarge(var context: Context, var listOfFluidItems:ArrayList<RV_FluidDataClass>): RecyclerView.Adapter<RV_AdapterFluidDischarge.MyViewHolderFluid>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RV_AdapterFluidDischarge.MyViewHolderFluid {
        return(MyViewHolderFluid(LayoutInflater.from(context).inflate(R.layout.recycler_view_ticket,parent,false)))
    }

    override fun getItemCount(): Int {
        return listOfFluidItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolderFluid, position: Int) {
        val fluid_items = listOfFluidItems[position]
        holder.tv_fluid_image.text = fluid_items.fluid_problem_name
        holder.iv_fluid_image.setImageResource(fluid_items.fluid_problem_image)
        holder.iv_fluid_problem_image_color.setImageResource(fluid_items.fluid_problem_image_color)

        holder.constraint_layout_rv_ticket.setOnClickListener {
            holder.iv_fluid_problem_image_color.setImageResource(R.color.red)
            Toast.makeText(context, holder.tv_fluid_image.text, Toast.LENGTH_SHORT).show()


             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                 val current = LocalDateTime.now()
                 val formatter = DateTimeFormatter.BASIC_ISO_DATE
                 val current_date = current.format(formatter)

                 Thread {
                     val db = Room.databaseBuilder(context!!, RoomDB::class.java, "RoomDB").build()
                     try {
                         db.FluidDao().insert_in_FluidEntity(
                             FluidEntity(
                                 current_date,
                                 Calendar.MONTH,
                                 Calendar.DAY_OF_MONTH,
                                 holder.tv_fluid_image.text.toString())
                         )

                     }catch (e:Exception){

                         Log.d("Exception",e.toString())

                     }
                     db.FluidDao().retrieveData_from_fluid_entity().forEach {
                         Log.i("tag",it.Current_date)
                         Log.i("tag",it.current_month.toString())
                         Log.i("tag",it.current_day.toString())
                         Log.i("tag",it.FluidColumn)

                     }


                 }.start()

             } else {
                TODO("VERSION.SDK_INT < O")
            }



        }



    }

    inner class MyViewHolderFluid(itemview: View): RecyclerView.ViewHolder(itemview){
        var tv_fluid_image = itemview.tv_problem_title
        var iv_fluid_image = itemview.iv_physical_param_image
        var iv_fluid_problem_image_color = itemview.iv_physical_param_image_color
        var constraint_layout_rv_ticket = itemview.constraint_layout_rv_ticket

    }


}