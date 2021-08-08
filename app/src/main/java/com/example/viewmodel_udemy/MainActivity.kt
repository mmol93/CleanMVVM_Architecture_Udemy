package com.example.viewmodel_udemy

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.MainRecyclerAdapter
import com.example.database.SubscribeRepository
import com.example.database.Subscriber
import com.example.database.SubscriberDatabase
import com.example.viewmodel_udemy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binder : ActivityMainBinding
    private lateinit var subscribeViewModel: SubscribeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Room의 데이터 베이스 생성
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        // dao에서 설정한 기능들(Query, insert, delete 등)을 실시하는 클래스 객체 생성
        val repository = SubscribeRepository(dao)
        // ViewModel을 초기화하는 ViewModelFactory 만들기
        val factory = SubscriberViewModelFactory(repository)
        // viewModel과 liveData 묶기
        subscribeViewModel = ViewModelProvider(this, factory).get(SubscribeViewModel::class.java)
        binder.myViewModel = subscribeViewModel
        binder.lifecycleOwner = this

        initRecyclerView()
    }
    private fun initRecyclerView(){
        binder.subscribeRecyclerView.layoutManager = LinearLayoutManager(this)
        displaySubscribersList()
    }
    private fun displaySubscribersList(){
        // getSaveSubscribers를 observe한다
        subscribeViewModel.getSaveSubscribers().observe(this, Observer {
            Log.d("test", it.toString())
            // adapter에 람다식을 넘겨준다
            binder.subscribeRecyclerView.adapter = MainRecyclerAdapter(it) { selectedItem: Subscriber ->
                listItemClicked(selectedItem)
            }
        })
    }
    // 이 함수는 Adapter로 넘겨서 각 item이 해당 리스너와 연결되게 만든다
    // 만드는 방법은 람다식을 이용한다
    private fun listItemClicked(subscriber: Subscriber){
        Toast.makeText(this, "selected name is ${subscriber.name}", Toast.LENGTH_SHORT).show()
        subscribeViewModel.initUpdateAndDelete(subscriber)
    }
}