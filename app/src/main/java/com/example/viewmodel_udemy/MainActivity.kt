package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.viewmodel_udemy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        const val KEY_COUNT_VALUE = "key"
    }
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

        // input data 설정 - 키, 값 구성으로 데이터를 넣을 수 있다
        val data = Data.Builder().putInt(KEY_COUNT_VALUE, 125).build()

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
            .setInputData(data)
            .build()

        // 다른 workRequest를 추가하고 싶을 때도 똑같이 workRequest를 정의해주며된다
        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()
        val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()

        // enqueue를 사용하여 workRequest를 workManager에 제출한다
        WorkManager.getInstance(applicationContext)
                // begin -> then 입력 "순서대로" 일이 진행된다
            .beginWith(filteringRequest).then(compressingRequest).then(uploadRequest)
                // begin으로 시작해서 then으로 끝났다면 enqueue에 공백이 들어간다
            .enqueue()

        // WorkManager 인스턴스의 id를 이용하여 LiveData로 observe를 할 수 있다
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
                // workManager의 상태를 observe 한다
            .observe(this, Observer {
                // state: Blocked -> Enqueued -> Running -> Succeeded
                binder.textView.text = it.state.name
                // Worker에서 보낸 값을 받는다
                if (it.state.isFinished){
                    val data = it.outputData
                    val message = data.getString(UploadWorker.KEY_WORKER)

                    Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                }
            })
    }
}