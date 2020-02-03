package com.example.enactus


import NotificationHelper
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat



class AlertReciever: BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {
        val notificationHelper = NotificationHelper(context)
        val nb: NotificationCompat.Builder = notificationHelper.channelNotification
        notificationHelper.manager.notify(1, nb.build())
    }
}