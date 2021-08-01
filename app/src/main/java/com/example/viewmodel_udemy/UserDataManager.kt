package com.example.viewmodel_udemy

import android.util.Log
import kotlinx.coroutines.*

class UserDataManager {
    suspend fun getTotalUserCount() : Int{
        var count = 0
        // (대문자)CoroutineScope: 인터페이스
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            count = 50
            Log.d("test", "IN_count: $count")
        }
        val deferred = CoroutineScope(Dispatchers.IO).async {
            delay(3000)
            Log.d("test", "IN_deferred: 70")
            return@async 70
        }
        Log.d("test", "OUT_count: $count")
        Log.d("test", "OUT_deferred: $deferred")

        // 첫 번째 변수인 count는 기다리지 않기 때문에 초기 값인 0이 바로 들어가고
        // 두 번째 변수인 deferred는 await()가 있기 때문에 해당 변수만 끝날 때까지 기다렸다가 넣어준다
        // 즉, delay에 의해 deferred 값을 얻기 위해 3초를 기다리지만 count는 return에 반영되지 않음
        // 그래서 이러한 방법은 권장되지 않는 사용법임
        return count + deferred.await()
    }
}