package com.example.viewmodel_udemy

import android.app.Application

class App : Application() {
    lateinit var dataComponent: DataComponent
    override fun onCreate() {
        dataComponent = DaggerDataComponent.builder().build()
        super.onCreate()
    }
}