package com.example.viewmodel_udemy

import dagger.Component

// Component는 Interface 또는 abstract class에서만 사용가능
// Inject를 다 지정하고 Component까지 지정한 후 프로젝트를 rebuild하면 생성된(generated) Dagger클래스를 얻을 수 있다
// 이름은 Dagger + Interface의 이름이 된다 => 여기선: DaggerSmartPhoneComponent
// 해당 클래스는 Dagger의 주된 열할을 수행하는 부분임
@Component
interface SmartPhoneComponent {
    fun getSmartPhone() : SmartPhone
}