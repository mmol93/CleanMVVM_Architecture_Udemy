package com.example.viewmodel_udemy

import dagger.Component
import javax.inject.Singleton

// Component는 Interface 또는 abstract class에서만 사용가능
// Inject를 다 지정하고 Component까지 지정한 후 프로젝트를 rebuild하면 생성된(generated) Dagger클래스를 얻을 수 있다
// 이름은 Dagger + Interface의 이름이 된다 => 여기선: DaggerSmartPhoneComponent
// 해당 클래스는 Dagger의 주된 열할을 수행하는 부분임
// module: module을 추가한다
// 싱글턴 사용을 위해 Component에서 싱글턴 어노테이션을 붙인다
@Singleton
@Component(modules = [BatteryModule::class, MemoryCardModule::class])
interface SmartPhoneComponent {
    // 이 메서드는 MainActivity에서 사용된다
    // 사실 이는 필요한 모든 Activity에 MainActivity에서 처럼 선언해서 사용해야할 필요가 있다
    // -> Activity가 많아진다면 모든 Activity에 이 짓을 반복해야한다
//    fun getSmartPhone() : SmartPhone

    // 위 문제는 해당 Activity에서 @Inject 객체 생성을 통해 해결 가능함
    fun inject(mainActivity: MainActivity)
}