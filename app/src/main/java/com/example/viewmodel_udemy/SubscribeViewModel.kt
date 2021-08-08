package com.example.viewmodel_udemy

import androidx.lifecycle.*
import com.example.database.SubscribeRepository
import com.example.database.Subscriber
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// ViewModel에 대해 정의하기
class SubscribeViewModel(private val repository: SubscribeRepository) : ViewModel() {
    val subscribes = repository.subscribes
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete : Subscriber
    // 입력한 이름 및 이메일을 받는 변수 설정
    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()

    // 버튼 텍스트를 LiveData로 하여 무언가 변경이 있을 때는 그게 View에 반영되게 한다
    // 물론 LiveData가 뷰에 제대로 반영되기 위해 View에도 LiveData 설정을 해준다
    val saveUpdateButtonText = MutableLiveData<String>()
    val clearDeleteButtonText = MutableLiveData<String>()

    // String을 wrapper 클래스로 사용한다
    private val statusMessage = MutableLiveData<Event<String>>()
    // statusMessage를 받을 수 있는 getter 만들기
    val message : LiveData<Event<String>>
        get() = statusMessage

    init {
        saveUpdateButtonText.value = "Save"
        clearDeleteButtonText.value = "Clear All"
    }

    fun saveUpdate(){
        if (isUpdateOrDelete){
            // 새롭게 subscribe 객체를 가져온다
            subscriberToUpdateOrDelete.name = inputName.value!!
            subscriberToUpdateOrDelete.email = inputEmail.value!!
            update(subscriberToUpdateOrDelete)
        }else{
            val name = inputName.value!!
            val email = inputEmail.value!!

            // Room은 id가 0인 것을 무시하고 자동으로 하나씩 올려줄것이다
            // (autoinrement 활성화했을 시)
            insert(Subscriber(0, name, email))
            inputName.value = null
            inputEmail.value = null
        }
    }

    fun clearAllDelete(){
        // 위에서 정의한 isUpdateOrDelete 트리거 이용
        if (isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)
        }else{
            deleteAll()
        }
    }

    fun insert(subscriber: Subscriber){
        viewModelScope.launch {
            val newRowId : Long = repository.insert(subscriber)
            if (newRowId > -1){
                statusMessage.value = Event("Subscriber Insert Successfully : $newRowId")
            }else{
                statusMessage.value = Event("Subscriber Insert Failed")
            }
        }
    }

    fun update(subscriber: Subscriber){
        viewModelScope.launch {
            val noOfRows = repository.update(subscriber)
            if (noOfRows > 0){
                // subscriber에서 해당 데이터 베이스가 선택되게 한다
                // 어떤 데이터 베이스의 인덱스에 대한 값이 들어갈지는 adapter에 넣어서
                // listItemClicked와 같이 발동되게 한다
                inputName.value = null
                inputEmail.value = null
                isUpdateOrDelete = false
                subscriberToUpdateOrDelete = subscriber
                saveUpdateButtonText.value = "Save"
                clearDeleteButtonText.value = "ClearAll"
                statusMessage.value = Event("$noOfRows Row Subscriber Update Successfully")
            }else{
                statusMessage.value = Event("Update Error")
            }
        }
    }

    fun delete(subscriber: Subscriber){
        viewModelScope.launch {
            val noOfRowsDeleted = repository.delete(subscriber)
            if (noOfRowsDeleted > 0){
                // subscriber에서 해당 데이터 베이스가 선택되게 한다
                // 어떤 데이터 베이스의 인덱스에 대한 값이 들어갈지는 adapter에 넣어서
                // listItemClicked와 같이 발동되게 한다
                inputName.value = null
                inputEmail.value = null
                isUpdateOrDelete = false
                subscriberToUpdateOrDelete = subscriber
                saveUpdateButtonText.value = "Save"
                clearDeleteButtonText.value = "ClearAll"
                statusMessage.value = Event("Subscriber Delete Successfully")
            }else{
                statusMessage.value = Event("Delete Error")
            }

        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            val noOfRowsDeleted = repository.deleteAll()
            if (noOfRowsDeleted > 0){
                statusMessage.value = Event("$noOfRowsDeleted Subscribers are Cleared Successfully")
            }else{
                statusMessage.value = Event("All Clear Error")
            }
        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        // subscriber에서 해당 데이터 베이스가 선택되게 한다
        // 어떤 데이터 베이스의 인덱스에 대한 값이 들어갈지는 adapter에 넣어서 listItemClicked와 같이 발동되게 한다
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveUpdateButtonText.value = "Update"
        clearDeleteButtonText.value = "Delete"
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