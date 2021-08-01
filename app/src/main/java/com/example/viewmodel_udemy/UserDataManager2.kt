package com.example.viewmodel_udemy

import android.util.Log
import kotlinx.coroutines.*

class UserDataManager2 {
    var count = 0
    // async로 시작한 코로틴은 Deferred 객체를 반환
    lateinit var deferred: Deferred<Int>
    suspend fun getTotalUserCount() : Int {
        // (소문자)coroutineScope: suspend fun
        // 부모 코루틴의 자식 코루틴이 될 수 있다
        coroutineScope {
            // suspend fun의 coroutine의 경우 Dispatchers를 launch에서 설정한다
            launch(Dispatchers.IO) {
                delay(1000)
                count = 50
                Log.d("test", "IN_count: $count")
            }
            deferred = async(Dispatchers.IO) {
                delay(3000)
                Log.d("test", "IN_deferred: 70")
                return@async 70
            }
        }
        // launch와 deferred가 같은 코루틴 내에 있기 때문에
        // 더 늦게 끝나는 deferred가 끝나고나서 count 결과도 같이 가져온다
        return count + deferred.await()
    }
}