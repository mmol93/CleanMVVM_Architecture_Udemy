package com.example.viewmodel_udemy

import dagger.Component

@Component(modules = [DataModule::class])
interface DataComponent {
    fun inject(mainActivity: MainActivity)
}