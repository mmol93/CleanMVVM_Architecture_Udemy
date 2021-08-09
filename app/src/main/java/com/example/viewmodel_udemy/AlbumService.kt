package com.example.viewmodel_udemy

import retrofit2.Response
import retrofit2.http.*

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

    // 예: https://jsonplaceholder.typicode.com/albums/3
    @GET("/albums/{id}")
    // Response<Albums> 이 아니라 Response<AlbumsItem>인 이유
    // : @Query의 경우 Sort로써 정렬된 값"들"이 나온다 그래서 값들의 List인 Albums를 사용
    // : @Path의 경우 해당 Source(여기선 id)만 가져오기 때문에 1개의 AlbumItem 형태를 띄는 행렬 1개만 가져온다
    // -> @Query와 @Path의 경우 return 하는 값의 type 자체가 다르다
    suspend fun getAlbum(@Path("id") albumId:Int) : Response<AlbumsItem>

    // @Body: Body로써 POST Request를 하는 것을 의미
    @POST("/albums")
    suspend fun uploadAlbum(@Body album:AlbumsItem):Response<AlbumsItem>
}