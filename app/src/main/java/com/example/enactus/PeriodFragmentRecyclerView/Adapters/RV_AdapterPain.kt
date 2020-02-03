package com.example.enactus.PeriodFragmentRecyclerView.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enactus.PeriodFragmentRecyclerView.DataClass.RV_PainDataClass
import com.example.enactus.R
import kotlinx.android.synthetic.main.recycler_view_ticket.view.*


class RV_AdapterPain(var context: Context, var listOfPainItems:ArrayList<RV_PainDataClass>):RecyclerView.Adapter<RV_AdapterPain.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_ticket,parent,false))
    }

    override fun getItemCount(): Int {
        return listOfPainItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var pain_item = listOfPainItems[position]
        holder.tv_pain_title.text = pain_item.pain_title
        holder.iv_pain_image.setImageResource(pain_item.pain_image)
        holder.iv_pain_image_color.setImageResource(pain_item.pain_image_color)

    }

    inner class MyViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        var tv_pain_title = itemview.tv_problem_title
        var iv_pain_image = itemview.iv_physical_param_image
        var iv_pain_image_color = itemview.iv_physical_param_image_color

    }
}
