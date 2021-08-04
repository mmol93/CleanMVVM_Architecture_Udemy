package com.example.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// DAO에 관한 정의임을 명시
@Dao
interface SubscriberDAO {
    // @Insert: 해당 정보(테이블, 컬러명 등)로 DB를 만든다
    // Room은 main 스레드에 있는 데이터 베이스에 접근할 수 없다
    // 그래서 suspend fun를 이용해서 background에서 작동하게 한다
    // OnConflictStrategy.REPLACE: 해당 인덱스에 이미 값이 있을 경우 replace 한다
    // Long 반환을 사욯하면 rowId를 반환받을 수 있다
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(subscriber: Subscriber) : Long

    // DB에 있는 모든 데이터를 갱신한다
    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    //
    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    // 아래 함수는 코루틴을 백그라운드에서 사용하고 있을 때 제거할 필요가 없다
    // = 즉, Room이 존재하는한 계속 사용하고 있어야한다
    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers() : Flow<List<Subscriber>>

}