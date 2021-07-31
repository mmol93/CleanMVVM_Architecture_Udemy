package com.example.viewmodel_udemy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel() : ViewModel() {
    val userName = MutableLiveData<String>()

    init {
        userName.value = "Frank"
    }
}