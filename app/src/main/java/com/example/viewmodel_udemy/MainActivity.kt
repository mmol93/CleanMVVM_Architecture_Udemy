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
        // 1개 이상의 module을 component에 넣을 경우 create() 메서드를 사용할 수 없다
//        DaggerSmartPhoneComponent.create().inject(this)
//        // 의존성을 가진 객체를 통해 메서드 실행가능
//        smartPhone.makeACallWithRecording()

        // 모듈이 2개 이상일 때는 builder를 통해 어떤 모듈을 실행할지 정의해줘야한다
//        DaggerSmartPhoneComponent.builder()
//            .memoryCardModule(MemoryCardModule(1000)).build().inject(this)

        // Application Class를 이용하여 모든 Class에서 Component 사용 가능
        (application as SmartPhoneApplication).smartPhoneComponent.inject(this)
    }
}