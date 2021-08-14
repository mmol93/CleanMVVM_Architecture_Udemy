package com.example.viewmodel_udemy

import android.util.Log
import javax.inject.Inject

// interface를 통해 의존성을 갖게 해보기
// interface의 경우 객체를 생성할 수 없기 때문에 SmartPhone 클래스에서 매개변수가 될 수 없다
// 별도로 Bettery 인터페이스를 상속받는 클래스를 또 만든다 -> NickelBattery 클래스 생성
interface Battery{
    fun getPower()
}