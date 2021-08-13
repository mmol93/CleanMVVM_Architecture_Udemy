package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// 참조 웹: https://www.charlezz.com/?p=1259
// dagger의 어노테이션 설명: https://jaejong.tistory.com/125
// 안드 공식문서: https://developer.android.com/training/dependency-injection/dagger-android?hl=ko

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        DaggerSmartPhoneComponent.create().getSmartPhone().makeACallWithRecording()
    }
}