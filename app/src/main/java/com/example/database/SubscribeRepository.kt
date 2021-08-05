package com.example.database

// DAO 인터페이스를 생성자로 가져온다 -> DAO에 있는 요소를 사용할 예정
// 여기선 SubscriberDAO에서 정의한 함수를 실행해주는 메서드를 만든다
// (실제로는 이렇게 repositoty를 거치지 않고 바로 DAO에서 호출해도됨)
// (지금은 MVVM 공부를 위해서 이렇게 한거임)
class SubscribeRepository(private val dao : SubscriberDAO) {
    val subscribes = dao.getAllSubscribers()

    // Room 라이브러리는 자동으로 데이터를 liveData로 처리하기 때문에
    // suspend fun으로 기능을 호출한다
    suspend fun insert(subscriber: Subscriber){
        dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber){
        dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber){
        dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}