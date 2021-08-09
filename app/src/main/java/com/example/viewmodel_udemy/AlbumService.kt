package com.example.viewmodel_udemy

import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    // Retrofit과 코루틴을 이용하여 값을 얻기 위해 suspend 사용
    // Retforit은 항상 Retrofit Response object를 반환한다
    // 반환값 Response에는 항상 JSON 데이터 값이 들어있다(이 값은 역직렬화된 이후의 값임)
    // 그래서 Kotlin에서 읽을 수 있음
    @GET("/albums")
    suspend fun getAlbums() : Response<Albums>
}