package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
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
        // WorkManager 객체 생성
        val workManager = WorkManager.getInstance(applicationContext)
        // WorkManager에서 사용가능한 Constraints
        // setRequiresCharging: 기기가 현재 충전중일 때 WorkRequest를 할 수 있게 한다
        // https://developer.android.com/reference/kotlin/androidx/work/Constraints.Builder
        val constraints = Constraints.Builder().setRequiresCharging(true)
            // Constraints는 2개 이상도 설정할 수 있다
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        // WorkManager의 실행은 WorkManager 서비스로 할 수 있다
        // OneTimeWorkRequest: 한 번만 실행한다
        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            // 설정한 Constraints를 넣는다
            .setConstraints(constraints)
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