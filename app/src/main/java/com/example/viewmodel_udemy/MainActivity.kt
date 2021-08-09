package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.viewmodel_udemy.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binder : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // RetrofitInstance에 있는 BASE_URL을 이용하여 접근
        // AlbumService에 있는 End Point를 이용하여 대상 페이지에 접근
        // GSON Converter를 이용하여 역직렬화 한다 = retService
        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        val responseLiveData : LiveData<Response<Albums>> = liveData {
            val response = retService.getAlbums()
            // getAlbums 객체 값을 liveData로 지정한다
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            // listIterator: 자바 컬렉션(ArrayList, vector 등)에서 항목을 탐색하는데 쓰이는 커서
            val albumsList = it.body()?.listIterator()
            if (albumsList != null){
                while (albumsList.hasNext()){
                    val albumsItem = albumsList.next()
                    val result = " " + "Album id: ${albumsItem.title}\n"
                    binder.textView.append(result)
                }
            }
        })
    }
}