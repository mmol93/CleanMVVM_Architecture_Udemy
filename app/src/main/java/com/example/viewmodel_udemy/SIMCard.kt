package com.example.viewmodel_udemy

import android.util.Log
import javax.inject.Inject

// SIMCard 생성자에 의존성 부여
class SIMCard @Inject constructor(private val serviceProvider: ServiceProvider){
    init {
        Log.d("test", "SIMCard constructed")
    }
    fun getConnection(){

    }
}