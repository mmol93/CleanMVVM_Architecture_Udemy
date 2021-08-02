package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel_udemy.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var binder : ActivityMainBinding
    private lateinit var mainActivityViewModel : MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // 뷰모델 대상 지정(MainActivityViewModel)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getUserData()
        // users 변수를 감시하게 한다
        // 여기서 users의 경우 변수가 자기 자신 1개이기 때문에 myUsers = users라고 할 수 있다
        mainActivityViewModel.users.observe(this, Observer {myUsers ->
            // UserRepository 클래스에서 데이터 추가는 8초 뒤에 하기 때문에 Observe에 발각되어 출력되게 된다
            myUsers.forEach{
                Log.d("test", "name is :${it.name}")
            }
        })
        setContentView(binder.root)
    }
}