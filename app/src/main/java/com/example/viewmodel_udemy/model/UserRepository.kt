package com.example.viewmodel_udemy.model

import kotlinx.coroutines.delay

// User 클래스 형태의 데이터를 List에 넣는 클래스 및 함수
class UserRepository {
    suspend fun getUsers() : List<User>{
        // Obserbe에 걸릴 수 있게 delay를 8초 준다
        delay(8000)
        val users : List<User> = listOf(
            User(id = 1, "sam"),
            User(id = 2, "sbm"),
            User(id = 3, "scm"),
            User(id = 4, "sdm")
        )
        return users
    }
}