package com.example.viewmodel_udemy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.database.SubscribeRepository
import com.example.database.Subscriber
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// ViewModel에 대해 정의하기
class SubscribeViewModel(private val repository: SubscribeRepository) : ViewModel() {
    val subscribes = repository.subscribes
    // 입력한 이름 및 이메일을 받는 변수 설정
    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()

    // 버튼 텍스트를 LiveData로 하여 무언가 변경이 있을 때는 그게 View에 반영되게 한다
    // 물론 LiveData가 뷰에 제대로 반영되기 위해 View에도 LiveData 설정을 해준다
    val saveUpdateButtonText = MutableLiveData<String>()
    val clearDeleteButtonText = MutableLiveData<String>()

    init {
        saveUpdateButtonText.value = "Save"
        clearDeleteButtonText.value = "Clear All"
    }

    fun saveUpdate(){
        val name = inputName.value!!
        val email = inputEmail.value!!

        // Room은 id가 0인 것을 무시하고 자동으로 하나씩 올려줄것이다
        // (autoinrement 활성화했을 시)
        insert(Subscriber(0, name, email))
        inputName.value = null
        inputEmail.value = null
    }

    fun clearAllDelete(){
        deleteAll()
    }

    fun insert(subscriber: Subscriber){
        viewModelScope.launch {
            repository.insert(subscriber)
        }
    }

    fun update(subscriber: Subscriber){
        viewModelScope.launch {
            repository.update(subscriber)
        }
    }

    fun delete(subscriber: Subscriber){
        viewModelScope.launch {
            repository.delete(subscriber)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    // liveData로써 해당 값을들 가져온다
    fun getSaveSubscribers() = liveData {
        // collect: Flow 인터페이스에서 값을 가져오는 함수
        repository.subscribes.collect {
            // emit: 코루틴 LiveData에서 liveData를 주어진 값으로 설정하는 것
            emit(it)
        }
    }
}