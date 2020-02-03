package com.example.enactus.PeriodFragmentRecyclerView.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.enactus.PeriodFragmentRecyclerView.DataClass.RV_FluidDataClass
import com.example.enactus.R
import kotlinx.android.synthetic.main.recycler_view_ticket.view.*


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
            Toast.makeText(context, holder.tv_fluid_image.text,Toast.LENGTH_SHORT).show()
        }

    }

    inner class MyViewHolderFluid(itemview: View): RecyclerView.ViewHolder(itemview){
        var tv_fluid_image = itemview.tv_problem_title
        var iv_fluid_image = itemview.iv_physical_param_image
        var iv_fluid_problem_image_color = itemview.iv_physical_param_image_color
        var constraint_layout_rv_ticket = itemview.constraint_layout_rv_ticket

    }


}