package com.example.enactus.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enactus.R
import com.example.enactus.RecyclerViewAdapter
import com.example.enactus.RecyclerViewDataClass
import kotlinx.android.synthetic.main.fragment_period.view.*


class FragmentPeriod : Fragment() {

    var listOfItems = ArrayList<RecyclerViewDataClass>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val myfragment_period = inflater.inflate(R.layout.fragment_period, container, false)

       loadRecyclerViewList()

        val rv = myfragment_period.recycler_view as RecyclerView
        val rv_pain_and_symptoms = myfragment_period.rv_pain_and_symptoms as RecyclerView
        val rv_fluid_vaginal_discharge = myfragment_period.rv_fluid_vaginal_discharge as RecyclerView
        val rv_Pill = myfragment_period.rv_Pill as RecyclerView
        val rv_bleeding = myfragment_period.rv_bleeding as RecyclerView
        val rv_Sleep = myfragment_period.rv_Sleep as RecyclerView
        val rv_Energy = myfragment_period.rv_Energy as RecyclerView
        val rv_Moods = myfragment_period.rv_Moods as RecyclerView
        val rv_Sex = myfragment_period.rv_Sex as RecyclerView
        val rv_State_of_mind = myfragment_period.rv_State_of_mind as RecyclerView

        rv_pain_and_symptoms.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_pain_and_symptoms.adapter = RecyclerViewAdapter(context!!,listOfItems)

        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv.adapter = RecyclerViewAdapter(context!!,listOfItems)

        rv_fluid_vaginal_discharge.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_fluid_vaginal_discharge.adapter = RecyclerViewAdapter(context!!,listOfItems)

        rv_Pill.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_Pill.adapter = RecyclerViewAdapter(context!!,listOfItems)

        rv_bleeding.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_bleeding.adapter = RecyclerViewAdapter(context!!,listOfItems)

        rv_Sleep.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_Sleep.adapter = RecyclerViewAdapter(context!!,listOfItems)

        rv_Energy.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_Energy.adapter = RecyclerViewAdapter(context!!,listOfItems)

        rv_Moods.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_Moods.adapter = RecyclerViewAdapter(context!!,listOfItems)

        rv_Sex.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_Sex.adapter = RecyclerViewAdapter(context!!,listOfItems)

        rv_State_of_mind.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rv_State_of_mind.adapter = RecyclerViewAdapter(context!!,listOfItems)

        return myfragment_period
    }

    private fun loadRecyclerViewList(){
        listOfItems.add(RecyclerViewDataClass(R.drawable.facebook_logo,"google"))
        listOfItems.add(RecyclerViewDataClass(R.drawable.facebook_logo,"google"))
        listOfItems.add(RecyclerViewDataClass(R.drawable.facebook_logo,"google"))
        listOfItems.add(RecyclerViewDataClass(R.drawable.google_logo,"google"))
        listOfItems.add(RecyclerViewDataClass(R.drawable.google_logo,"google"))
        listOfItems.add(RecyclerViewDataClass(R.drawable.google_logo,"google"))
        listOfItems.add(RecyclerViewDataClass(R.drawable.google_logo,"google"))
        listOfItems.add(RecyclerViewDataClass(R.drawable.facebook_logo,"google"))
        listOfItems.add(RecyclerViewDataClass(R.drawable.google_logo,"google"))
        listOfItems.add(RecyclerViewDataClass(R.drawable.google_logo,"google"))
    }



}
