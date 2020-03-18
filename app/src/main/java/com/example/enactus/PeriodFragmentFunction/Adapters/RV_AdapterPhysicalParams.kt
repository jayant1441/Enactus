package com.example.enactus.PeriodFragmentFunction.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enactus.Fragments.FragmentHome
import com.example.enactus.HeightWeightDataClass
import com.example.enactus.PeriodFragmentFunction.DataClass.RecyclerViewDataClass
import com.example.enactus.R
import com.example.enactus.SleepTimeDataClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recycler_view_ticket.view.iv_physical_param_image
import kotlinx.android.synthetic.main.recycler_view_ticket.view.iv_physical_param_image_color
import kotlinx.android.synthetic.main.rv_ticket_physical_parms.view.*


class RV_AdapterPhysicalParams(var context: Context, var listOfItems:ArrayList<RecyclerViewDataClass>):RecyclerView.Adapter<RV_AdapterPhysicalParams.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_ticket_physical_parms,parent,false))
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = listOfItems[position]
        holder.iv_phy_param_icon.setImageResource(item.Phy_param_Image)
        holder.tv_phy_param_name.text = item.Phy_param_Title
        holder.iv_phy_param_icon_color.setImageResource(item.Phy_param_Image_color)

        val current_user_uid = FirebaseAuth.getInstance().currentUser!!.uid
        val databaseReference = FirebaseDatabase.getInstance().getReference("all-data/${current_user_uid}")

        if (position == 0 ){
            val weight_height_ref = databaseReference.child("weight-height-data")
            weight_height_ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val data_weight_height = p0.getValue(HeightWeightDataClass::class.java)
                    holder.tv_phy_param_des.text = data_weight_height!!.weight + " kg"

                }

            })
        }
        else if (position == 1){
            val weight_height_ref = databaseReference.child("weight-height-data")
            weight_height_ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val data_weight_height = p0.getValue(HeightWeightDataClass::class.java)
                    holder.tv_phy_param_des.text = data_weight_height!!.height + " cm"

                }

            })
        }
        else if (position == 2){
            val sleep_data_ref = databaseReference.child("sleep-data")
            sleep_data_ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val sleepTime = p0.getValue(SleepTimeDataClass::class.java)
                    if (sleepTime!=null){
                        holder.tv_phy_param_des.text = sleepTime.sleepTime
                    }
                    else{
                        return
                    }
                }

            })
        }

    }

    inner class MyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        var tv_phy_param_name = itemview.tv_Phy_param_param
        var iv_phy_param_icon = itemview.iv_physical_param_image
        var iv_phy_param_icon_color = itemview.iv_physical_param_image_color
        var tv_phy_param_des = itemview.tv_phy_param_des

    }
}
