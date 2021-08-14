package com.example.viewmodel_udemy

import android.util.Log
import javax.inject.Inject

// Class는 Inject로 의존성 부여
// 인터페이스까지만 정의하면 Battery 클래스로 객체를 만들었을 때
// 여기서 재정의한 getPower() 함수를 사용할 수 없다 -> BatteryModule 클래스 생성
class NickelBattery @Inject constructor(): Battery {
    override fun getPower() {
        Log.d("test", "Power from NickelBattery")
    }
}