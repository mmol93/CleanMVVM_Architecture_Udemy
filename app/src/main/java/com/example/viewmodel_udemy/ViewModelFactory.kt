package com.example.viewmodel_udemy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// ViewModelFactory가 필요한 이유
// ViewModel에서 초기값을 설정 or 초기화 해줄 때 필요하다
// ViewModel에서 직접적으로 인스턴스 값을 설정하는 것은 불가능함
class ViewModelFactory(private val startingTotal : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(startingTotal) as T
        }
        throw IllegalAccessException("UnKnown View Model Class")
    }
}