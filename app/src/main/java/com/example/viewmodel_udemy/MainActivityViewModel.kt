package com.example.viewmodel_udemy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal : Int) : ViewModel() {
    // LiveData를 사용
    // 혹시 변수에 private를 사용한다면 get()을 써서 이 라이브데이터를 지정해줘야한다
    var total = MutableLiveData<Int>()

    init {
        // MutableLiveData의 경우 해당 값을 .value로 지정할 수 있다
        total.value = startingTotal
    }

    fun add(input : Int){
        // .value의 경우 자동으로 Int가 아니기 때문에 연산을 위해서는
        // 함수를 사용하거나 .toInt() 같은 것을 활용해야한다
        total.value = (total.value)?.plus(input)
    }
}