package com.example.enactus

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fragment_water.*

class ResetValueMidnight :BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {

        val mypref = context!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = mypref.edit()
        editor.putString("waterdrank", "0")
        editor.apply()
        Toast.makeText(context,"Value returned to zero",Toast.LENGTH_LONG).show()

    }


}
