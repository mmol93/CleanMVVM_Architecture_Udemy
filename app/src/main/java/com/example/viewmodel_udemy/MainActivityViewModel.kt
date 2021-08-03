package com.example.viewmodel_udemy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.viewmodel_udemy.model.User
import com.example.viewmodel_udemy.model.UserRepository
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {
    private var userRepository = UserRepository()

    // 아래의 비활성화된 부분과 동일한 역할을 한다
    // liveData ktx library version 2.2.0-alpha01 이상에서 사용가능
    // 해당 변수들을 liveData로 사용할 수 있게 한다(복수 지정 가능)
    // 아랫의 비활성화된 부분의 경우 일일이 값을 가져와서 새롭게 liveData에 지정했어야 했다
    // 이 방법은 한번에 묶어서 간단하게 지정할 수 있다
    var users = liveData(Dispatchers.IO) {
        val result = userRepository.getUsers()

        // 해당 변수를 liveData로 지정한다
        // emit에 들어갈 변수는 liveData이어야 한다
        emit(result)
    }

//    fun getUserData(){
//        // 뷰모델에 launch를 할 경우 해당 뷰의 lifecycle과 연동되어
//        // 해당 뷰가 distroy될 경우 자동으로 cancel 된다
//        viewModelScope.launch {
//            var result : List<User>
//            withContext(Dispatchers.IO){
//                // result에 userRepository에 정의한 User 데이터를 가져온다
//                result = userRepository.getUsers()
//            }
//            // MutableLiveData에 result를 넣는다
//            // 즉, MutableLiveData에 아까 정의한 userRepository 데이터를 넣는다
//            users.value = result
//        }
//    }
}