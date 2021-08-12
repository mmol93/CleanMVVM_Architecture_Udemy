package com.example.viewmodel_udemy

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

// WorkManager: 앱이 종료되거나 기기가 다시 시작되더라도 실행될 것으로 예상되는 신뢰할 수 있는 비동기 작업을 쉽게 예약할 수 있게 해주는 API
// Work를 상속받기 위해선 Context와 WorkerParameters가 필요하다
class DownloadWorker(context : Context, params : WorkerParameters) : Worker(context, params) {

    // doWork()는 백그라라운드 스레드에서 비동기적으로 실행됨
    override fun doWork(): Result {
        // doWork가 실패할 경우 에러가 발생한다
        try{
            for (i in 0 until 1000){
                Log.d("test", "Downloading: $i")
            }

            val time = SimpleDateFormat("dd/M/yyyy hh:mm")
            val currentData = time.format(Date())
            Log.d("test", "Completed: $currentData")

            // 성공 or 실패를 확인할 수 있다
            return Result.success()
        }catch (e:Exception){
            return Result.failure()
        }
    }
}