package com.example.viewmodel_udemy

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun getUsers(): List<User>{
        delay(8000)
        val users : List<User> = listOf(
            User(1, "a"),
            User(2, "b"),
            User(3, "c"),
            User(4, "d")
        )
        return users
    }
}