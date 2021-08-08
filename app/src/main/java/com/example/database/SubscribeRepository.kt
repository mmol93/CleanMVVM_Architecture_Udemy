package com.example.database

// DAO 인터페이스를 생성자로 가져온다 -> DAO에 있는 요소를 사용할 예정
// 여기선 SubscriberDAO에서 정의한 함수를 실행해주는 메서드를 만든다
// (실제로는 이렇게 repositoty를 거치지 않고 바로 DAO에서 호출해도됨)
// (지금은 MVVM 공부를 위해서 이렇게 한거임)
class SubscribeRepository(private val dao : SubscriberDAO) {
    val subscribes = dao.getAllSubscribers()

    // Room 라이브러리는 자동으로 데이터를 liveData로 처리하기 때문에
    // suspend fun으로 기능을 호출한다
    // Long을 반환 값으로 할 경우 넣은 데이터의 Id를 반납한다
    // 단, Long을 반환하는 조건은 하나의 데이터만 insert 했을 경우다
    suspend fun insert(subscriber: Subscriber) : Long{
        return dao.insertSubscriber(subscriber)
    }

    // insert 이외에 다른 쿼리는 Int를 반환한다
    suspend fun update(subscriber: Subscriber) : Int{
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber) : Int{
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAll()
    }
}