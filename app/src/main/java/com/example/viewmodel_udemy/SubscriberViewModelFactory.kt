package com.example.viewmodel_udemy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.database.SubscribeRepository
import java.lang.IllegalArgumentException

// 생성자로써 SubscriberRepository의 인스턴스 값을 가져온다
class SubscriberViewModelFactory(private val repository: SubscribeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscribeViewModel::class.java)){
            // 결과로는 SubscribeViewModel을 반환해준다
                // 즉, Factory의 역할은 SubscribeViewModel를 초기화하고 반환해주는 것이다
            return SubscribeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}