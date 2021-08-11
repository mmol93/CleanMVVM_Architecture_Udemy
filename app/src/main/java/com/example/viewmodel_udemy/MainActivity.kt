package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
        val workManager = WorkManager.getInstance(applicationContext)
        // WorkManager의 실행은 WorkManager 서비스로 할 수 있다
        // OneTimeWorkRequest: 한 번만 실행한다
        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .build()
        // enqueue를 사용하여 workRequest를 workManager에 제출한다
        WorkManager.getInstance(applicationContext)
            .enqueue(uploadRequest)

        // WorkManager 인스턴스의 id를 이용하여 LiveData로 observe를 할 수 있다
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
                // workManager의 상태를 observe 한다
            .observe(this, Observer {
                // state: Blocked -> Enqueued -> Running -> Succeeded
                binder.textView.text = it.state.name
            })
    }
}