package com.example.enactus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_ticket.view.*


class RecyclerViewAdapter(var context: Context,var listOfItems:ArrayList<RecyclerViewDataClass>):RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_ticket,parent,false))
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listOfItems[position]
        holder.iv_problem_icon.setImageResource(item.ProbleemImage)
        holder.tv_problem_name.text = item.ProblemName
    }

    inner class MyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        var tv_problem_name = itemview.tv_problem
        var iv_problem_icon = itemview.iv_problem

    }
}
