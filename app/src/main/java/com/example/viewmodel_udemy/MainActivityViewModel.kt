package com.example.viewmodel_udemy

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    // Job을 Scope에 넣어서 해당 Scope를 컨트롤 할 수 있게 한다
    private val myJob = Job()
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    fun getUserData(){
        myScope.launch {  }
    }

    // 혹시라도 코루틴이 필요없는데도 계속 실행되어 메모리 누수가
    // 발생할 가능성이 있다 -> onCleared() 필요
    override fun onCleared() {
        super.onCleared()
        // Scope에 넣은 Job을 캔슬하면 해당 코루틴 스코프가 정지한다
        myJob.cancel()
    }
}