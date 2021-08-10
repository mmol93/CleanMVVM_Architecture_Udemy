package com.example.viewmodel_udemy

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.databinding.DataBindingUtil
import com.example.viewmodel_udemy.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binder : ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_second)
        receiveInput()
    }

    // Notification에서 reply로 받은 값을 처리한다
    private fun receiveInput(){
        // MainActivity에서 넘겨준 keyCode랑 같은 값을 가져오게한다
        val KEY_REPLY = "key_reply"
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput != null){
            val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
            binder.replyTextView.text = inputString

            // 메시지를 보낸 후 새롭게 notification을 갱신한다
            // 그러기 위해서는 기존에 사용했던 channelId와 notificationId가 필요하다
            val channelID = "viewmodel_udemy.channel1"
            val notificationId = 45

            // notification 갱신
            val repliedNotification = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("Your reply received")
                .build()
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, repliedNotification)
        }
    }
}