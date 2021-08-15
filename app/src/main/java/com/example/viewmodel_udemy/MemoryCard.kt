package com.example.viewmodel_udemy

import android.util.Log
import javax.inject.Inject

// MemoryCard 생성자에 의존성 부여
class MemoryCard{
    init {
        Log.d("test", "MemoryCard Constructed")
    }
    fun getSpaceAvailability(){
        Log.d("test", "MemoryCard is Ok")
    }
}