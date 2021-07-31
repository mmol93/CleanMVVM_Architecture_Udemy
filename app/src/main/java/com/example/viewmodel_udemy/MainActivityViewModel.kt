package com.example.viewmodel_udemy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel() : ViewModel() {
    val number = MutableLiveData<Int>()

    init {
        number.value = 0
    }
}