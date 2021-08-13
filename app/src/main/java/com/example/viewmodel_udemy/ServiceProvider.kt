package com.example.viewmodel_udemy

import android.util.Log
import javax.inject.Inject

// ServiceProvider 생성자에 의존성 부여
class ServiceProvider @Inject constructor() {
    init {
        Log.d("test", "ServiceProvider Constructed")
    }

    fun getServiceProvider(){
        Log.d("test", "ServiceProvider Connected")
    }
}