package com.example.enactus.IntroSlider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enactus.R
import kotlinx.android.synthetic.main.intro_slider_ticket.view.*

class IntroSliderAdapter : RecyclerView.Adapter<IntroSliderAdapter.IntroViewHolder>() {

    inner class IntroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val iv_intro_icon = itemView.iv_intro_icon
        val tv_intro_title = itemView.tv_intro_title
        val tv_intro_des = itemView.tv_intro_des
        val ll_intro_slider = itemView.ll_intro_slider


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        return IntroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.intro_slider_ticket, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {

        if (position == 0) {
            holder.tv_intro_title.text = "Track all your periods"
            holder.tv_intro_des.text =
                "Helps you track all your periods, Symptoms and show them in effective way"
            holder.iv_intro_icon.setImageResource(R.drawable.warranty)
            holder.ll_intro_slider.setBackgroundResource(R.color.light_red)


        }
        if (position == 1) {
            holder.tv_intro_title.text = "Water Activities"
            holder.tv_intro_des.text =
                "Get Notification to get Hydrated, Water is Your Best Friend for Life"
            holder.iv_intro_icon.setImageResource(R.drawable.water_bottle_glass)
            holder.ll_intro_slider.setBackgroundResource(R.color.light_blue)

        }
        if (position == 2) {
            holder.tv_intro_title.text = "An App made for Mental Peace"
            holder.tv_intro_des.text =
                "Your goal is not to battle with the mind, but to witness the mind"
            holder.iv_intro_icon.setImageResource(R.drawable.chakra)
            holder.ll_intro_slider.setBackgroundResource(R.color.light_pink)

        }

    }
}