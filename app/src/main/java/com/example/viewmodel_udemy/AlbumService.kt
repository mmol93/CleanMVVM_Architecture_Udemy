package com.example.viewmodel_udemy

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
    // Retrofit과 코루틴을 이용하여 값을 얻기 위해 suspend 사용
    // Retforit은 항상 Retrofit Response object를 반환한다
    // 반환값 Response에는 항상 JSON 데이터 값이 들어있다(이 값은 역직렬화된 이후의 값임)
    // 그래서 Kotlin에서 읽을 수 있음
    @GET("/albums")
    suspend fun getAlbums() : Response<Albums>

    // 아래 문구는 실제로 이렇게 작동한다 -> https://jsonplaceholder.typicode.com/albums?userId=3
    // userId에서 받은 값 = 3 / @Query("userId") = /albums?userID
    // 즉 End Point는 albums?userId=3 이 된다  => userId가 3인 데이터 묶음만 찾을 수 있다
    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId")userId:Int) : Response<Albums>
}