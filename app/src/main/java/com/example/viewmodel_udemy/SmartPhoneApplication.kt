package com.example.viewmodel_udemy

import android.app.Application


class SmartPhoneApplication : Application() {
    lateinit var smartPhoneComponent: SmartPhoneComponent
    // 앱이 처음 실행되었을 때 뭔가 해야할 일이 있다면 Application의 onCreate에 만드는 것이 좋다
    override fun onCreate() {
        super.onCreate()
        smartPhoneComponent = initDagger()
    }

    // Dagger 초기화를 Application 클래스를 이용해서 할 수 있고
    // 이는 모든 클래스에서 공유가 가능하다
    private fun initDagger() : SmartPhoneComponent =
        DaggerSmartPhoneComponent.builder()
            .memoryCardModule(MemoryCardModule(1000)).build()
}