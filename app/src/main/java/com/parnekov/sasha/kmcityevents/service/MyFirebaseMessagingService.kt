package com.parnekov.sasha.kmcityevents.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.parnekov.sasha.kmcityevents.R
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        showNotification(remoteMessage!!.notification!!.title, remoteMessage.notification!!.body)
    }

    private fun showNotification(title: String?, body: String?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (notificationManager != null) {
            val channelId = "my_channel_01"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "chanel_name"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(channelId, name, importance)
                channel.enableLights(true)
                channel.lightColor = R.color.colorAccent
                channel.vibrationPattern= longArrayOf(0,1000,500,1000)
                notificationManager.createNotificationChannel(channel)
            }

            val mBuilder = NotificationCompat.Builder(this, channelId)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("Info")

            notificationManager.notify(Random().nextInt(), mBuilder.build())
        }
    }

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
    }
}
