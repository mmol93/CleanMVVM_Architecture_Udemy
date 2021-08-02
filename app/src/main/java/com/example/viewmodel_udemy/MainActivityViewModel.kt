package com.example.viewmodel_udemy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodel_udemy.model.User
import com.example.viewmodel_udemy.model.UserRepository
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {
private var userRepository = UserRepository()
    // MutableLiveData에 User 클래스의 데이터를 넣는다
    // viewModelScope와 연동하기 위해 MutableLiveData를 사용한다
    var users : MutableLiveData<List<User>> = MutableLiveData()

    fun getUserData(){
        // 뷰모델에 launch를 할 경우 해당 뷰의 lifecycle과 연동되어
        // 해당 뷰가 distroy될 경우 자동으로 cancel 된다
        viewModelScope.launch {
            var result : List<User>
            withContext(Dispatchers.IO){
                // result에 userRepository에 정의한 User 데이터를 가져온다
                result = userRepository.getUsers()
            }
            // MutableLiveData에 result를 넣는다
            // 즉, MutableLiveData에 아까 정의한 userRepository 데이터를 넣는다
            users.value = result
        }
    }
}