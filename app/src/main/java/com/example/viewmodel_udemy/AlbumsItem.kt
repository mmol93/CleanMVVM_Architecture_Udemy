package com.example.viewmodel_udemy


import com.google.gson.annotations.SerializedName
// 이 클래스는 JSON to Kotlin Class plugin으로 만듬
// JSON 데이터에 있는 각 항목들
data class AlbumsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)