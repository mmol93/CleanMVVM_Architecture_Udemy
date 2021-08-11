package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.viewmodel_udemy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binder : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binder.button.setOnClickListener {
            setOnTimeWorkRequest()
        }
    }

    private fun setOnTimeWorkRequest(){
        // WorkManager의 실행은 WorkManager 서비스로 할 수 있다
        // OneTimeWorkRequest: 한 번만 실행한다
        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .build()
        // enqueue를 사용하여 workRequest를 workManager에 제출한다
        WorkManager.getInstance(applicationContext)
            .enqueue(uploadRequest)
    }
}