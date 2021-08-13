package com.example.viewmodel_udemy

import android.util.Log
import javax.inject.Inject

// Battery 생성자에 의존성 부여
class Battery @Inject constructor(){
    init {
        Log.d("test", "Battery Constructed")
    }
    fun getPower(){

    }
}