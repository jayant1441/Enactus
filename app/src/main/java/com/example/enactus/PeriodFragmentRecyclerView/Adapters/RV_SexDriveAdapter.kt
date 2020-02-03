package com.example.enactus.PeriodFragmentRecyclerView.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enactus.PeriodFragmentRecyclerView.DataClass.RV_SexDataClass
import com.example.enactus.R
import kotlinx.android.synthetic.main.recycler_view_ticket.view.*

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

    }

    inner class MyViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var tv_sex_drive_title = itemview.tv_problem_title
        var iv_sex_drive_image = itemview.iv_physical_param_image
        var iv_sex_drive_image_color = itemview.iv_physical_param_image_color

    }
}
