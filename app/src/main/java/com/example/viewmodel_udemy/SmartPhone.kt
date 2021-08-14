package com.example.viewmodel_udemy

import android.util.Log
import javax.inject.Inject

// 각각 Battery, SIMCard, MemoryCard 클래스를 매개변수로 받는 클래스 생성
// -> SmartPhone 클래스는 이 셋 클래스가 없다면 객체를 생성할 수 없다 -> SmartPhone 클래스는 해당 클래스에 의존성을 가진다
// @Inject: 필드, 생성자, 메서드에 부착 가능ㅡ Component가 Module로부터 객체를 생성하여 넘겨준다 ㅁ
// Component는 @Inject를 통해 DI 대상을 확인할 수 있음
class SmartPhone @Inject constructor(val battery: Battery, val simCard: SIMCard, val memoryCard: MemoryCard) {
    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailability()
        Log.d("test", "SmartPhone Constructed")
    }
    fun makeACallWithRecording(){
        Log.d("test", "Calling~")
    }
}