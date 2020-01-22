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
import kotlinx.android.synthetic.main.fragment_home.view.*


class FragmentHome : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val myfragmenthome =  inflater.inflate(R.layout.fragment_home, container, false)




        return myfragmenthome
    }

}