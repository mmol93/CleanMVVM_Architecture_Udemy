package com.example.viewmodel_udemy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import com.example.viewmodel_udemy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val channelID = "viewmodel_udemy.channel1"
    private var notificationManager : NotificationManager? = null
    private lateinit var binder : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // notificationManager 객체 정의
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        createNotificationChannel(channelID, "DemoChannel", "This is notification")
        binder.button.setOnClickListener {
            displayNotification()
        }
    }
    private fun displayNotification(){
        val notificationId = 45
        // notification 클릭 시 SecondActivity 열기
        val tapResultIntent = Intent(this, SecondActivity::class.java)
            .apply {
                // Intent에 flag 속성을 넣을 수 있다
                // ex: notification으로 액티비티를 띄울 때 다른 모든 액티비티는 Stack에서 삭제한다 등등...
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        // PendingIntent 만들기
        val pendingIntent = PendingIntent.getActivity(this, 0, tapResultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
            .setContentTitle("Demo Notification")
            .setContentText("this is notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager?.notify(notificationId, notification)
    }

    // noficiation의 channel에 대한 정의를 한다
    private fun createNotificationChannel(id : String, name : String, channelDescription: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance)
            channel.description = channelDescription

            notificationManager?.createNotificationChannel(channel)
        }
    }
}