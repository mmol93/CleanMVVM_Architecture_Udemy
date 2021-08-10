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
import androidx.core.app.RemoteInput
import androidx.databinding.DataBindingUtil
import com.example.viewmodel_udemy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val channelID = "viewmodel_udemy.channel1"
    private var notificationManager : NotificationManager? = null
    private lateinit var binder : ActivityMainBinding
    // Notification에서 reply로 답을 했을 때 넘겨줄 keyCode
    private val KEY_REPLY = "key_reply"

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

        // Reply Action
        // 여기서 RemoteInput은 반드시 androidx를 import 해야한다
        val remoteInput : RemoteInput = RemoteInput.Builder(KEY_REPLY).run {
            // setLabel: editText의 hint 같은 개념
            setLabel("Inset your name here")
            build()
        }
        // SecondActivity를 그대로 사용할 예정이기 때문에 pendingIntent를 그대로 사용한다
        val replyAction = NotificationCompat.Action.Builder(0, "Reply", pendingIntent).addRemoteInput(remoteInput).build()

        // Action Button1
        val intent2 = Intent(this, DetailActivity::class.java)
            .apply {
                // Intent에 flag 속성을 넣을 수 있다
                // ex: notification으로 액티비티를 띄울 때 다른 모든 액티비티는 Stack에서 삭제한다 등등...
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        // PendingIntent 만들기
        val pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
        val action2 = NotificationCompat.Action.Builder(0, "Setting", pendingIntent2).build()

        // Action Button2
        val intent3 = Intent(this, SettingActivity::class.java)
            .apply {
                // Intent에 flag 속성을 넣을 수 있다
                // ex: notification으로 액티비티를 띄울 때 다른 모든 액티비티는 Stack에서 삭제한다 등등...
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

        // PendingIntent 만들기
        val pendingIntent3 = PendingIntent.getActivity(this, 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT)
        val action3 = NotificationCompat.Action.Builder(0, "Details", pendingIntent3).build()

        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
            .setContentTitle("Demo Notification")
            .setContentText("this is notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(pendingIntent)
            .addAction(action2)
            .addAction(action3)
                // Reply 넣을 때는 pendingIntent 대신해서 넣어준다
            .addAction(replyAction)
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