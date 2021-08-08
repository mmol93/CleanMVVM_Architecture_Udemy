package com.example.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity를 이용하여 테이블 이름 정의
@Entity(tableName = "subscriber_data_table")
data class Subscriber(
    // 컬럼명 & 변수형태 정의
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_name")
    var id : Int,
    @ColumnInfo(name = "subscriber_id")
    var name : String,
    @ColumnInfo(name = "subscriber_email")
    var email : String
)