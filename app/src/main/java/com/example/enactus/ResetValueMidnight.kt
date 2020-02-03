package com.example.enactus

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ResetValueMidnight :BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {
        var reset_value_shared_pref = context!!.getSharedPreferences("reset_value_pref", Context.MODE_PRIVATE)
        var editor_reset = reset_value_shared_pref.edit()
        editor_reset.putString("reset_value","0")
        editor_reset.apply()

        Toast.makeText(context,"reset",Toast.LENGTH_SHORT).show()


    }


}