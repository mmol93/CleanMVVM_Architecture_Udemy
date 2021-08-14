package com.example.viewmodel_udemy

import dagger.Binds
import dagger.Module
import dagger.Provides

// @Module: Component에 연결되어 의존선 객체를 생성하는 역할
//@Module
//class BatteryModule {
//    // Module 클래스에 의해 의존성이 필요한 메서드는 @Provide 선언
//    // Provides로 서언된 함수는 자동으로 객체를 만들어서 Component에 제공
//    // 예: val providesBattery = providesBattery()
//    @Provides
//    fun providesBattery(nickelBattery: NickelBattery) : Battery{
//        // NickelBattery 클래스에서 생성자에 @Inject를 했기 때문에
//        // return 값으로 NickelBattery 클래스 객체를 돌려줄 필요가 없다
//        return nickelBattery
//    }
//}

// 추상 클래스와 추상 메서드로도 할 수 있다.
@Module
abstract class BatteryModule{
    // 추상 메서드의 경우 @Binds로 선언을 해줘야한다
    @Binds
    abstract fun providesBattery(nickelBattery: NickelBattery) : Battery
}