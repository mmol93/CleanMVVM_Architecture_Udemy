package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

// 참조 웹: https://www.charlezz.com/?p=1259
// dagger의 어노테이션 설명: https://jaejong.tistory.com/125
// 안드 공식문서: https://developer.android.com/training/dependency-injection/dagger-android?hl=ko
class MainActivity : AppCompatActivity() {
    // 의존성을 가진 클래스 객체를 바로 가져와서 사용할 수 있다
    @Inject lateinit var smartPhone: SmartPhone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        DaggerSmartPhoneComponent.create().getSmartPhone().makeACallWithRecording()
        // 똑같이 Component를 만들고 연결한다
        DaggerSmartPhoneComponent.create().inject(this)
        // 의존성을 가진 객체를 통해 메서드 실행가능
        smartPhone.makeACallWithRecording()
    }
}