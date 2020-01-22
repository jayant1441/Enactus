package com.example.enactus.Fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.enactus.LoginActivity
import com.example.enactus.R
import kotlinx.android.synthetic.main.fragment_fragment_settings.view.*


class FragmentSettings : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val myfragment_settings =  inflater.inflate(R.layout.fragment_fragment_settings, container, false)

        val tv_login = myfragment_settings.tv_login as TextView
        val tv_delete_Account = myfragment_settings.tv_delete_Account as TextView
        val tv_log_out = myfragment_settings.tv_log_out as TextView
        val tv_contact_us = myfragment_settings.tv_contact_us as TextView
        val tv_about_us = myfragment_settings.tv_about_us as TextView


        tv_login.setOnClickListener {
            startActivity(Intent(context,LoginActivity::class.java))
        }

        return myfragment_settings
    }



}
