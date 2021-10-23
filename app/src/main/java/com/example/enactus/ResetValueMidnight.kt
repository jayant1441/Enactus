package com.example.enactus

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ResetValueMidnight : BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {

        val mypref = context!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = mypref.edit()
        editor.putString("waterdrank", "0")
        editor.apply()

    }


}
