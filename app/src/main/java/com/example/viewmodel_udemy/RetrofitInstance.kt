package com.example.viewmodel_udemy

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    // RetrofitInstance는 한 번만 생성하면된다
    companion object{
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        // 이 부분은 보일러 플레이트임 = 그냥 복사해서 다른 Retrofit 쓰는 플젝에 사용가능
        // 역직렬화를 통해 JSON 데이터를 Kotlin이 읽을 수 있게 한다
        fun getRetrofitInstance():Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL).
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).
            build()
        }
    }
}