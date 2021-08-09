package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        // path parameter example
        val pathResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(this, "title from path: $title", Toast.LENGTH_LONG).show()
        })

        val responseLiveData : LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbums(3)
            // getAlbums 객체 값을 liveData로 지정한다
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            // listIterator: 자바 컬렉션(ArrayList, vector 등)에서 항목을 탐색하는데 쓰이는 커서
            val albumsList = it.body()?.listIterator()
            if (albumsList != null){
                while (albumsList.hasNext()){
                    val albumsItem = albumsList.next()
                    val result = " " + "Album userID: ${albumsItem.userId}\n" +"Album title: ${albumsItem.title}\n\n"
                    binder.textView.append(result)
                }
            }
        })
    }
}