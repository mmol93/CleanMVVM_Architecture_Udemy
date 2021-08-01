package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.viewmodel_udemy.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var binder : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setContentView(binder.root)

        // 클릭할 때 마다 카운트를 증가시키며 텍스트뷰에 표시
        binder.clickButton.setOnClickListener {
            binder.countTextView.text = count++.toString()
        }

        binder.downloadButton.setOnClickListener {
            // 메인스레드에서 처리하는 무거운 일을 코루틴으로 시키기
            // CoroutineScope: 코루틴 스코프를 사용하기 위한 인터페이스
            // GlobalScope: 코루틴중에서 최상위 등급 = 앱이 종료될 때까지 실행됨
            // Dispatchers: 코로틴을 어느 코루틴에서 실행할지 정의
            // launch: 코루틴 빌더, 새로운 코루틴을 생성 & 실행
            CoroutineScope(Dispatchers.Main).launch {
//                binder.userTextView.text = UserDataManager().getTotalUserCount().toString()
                binder.userTextView.text = UserDataManager2().getTotalUserCount().toString()
            }
        }
    }

    // 메인스레드를 로그로 계속 출력하게 하는 함수
    // suspend를 이용하여 코루틴에서 사용될 함수라는 것을 알린다
    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            // UI와 관계가 있는 코루틴의 경우 Main thread에서 실행하게 한다
            // withContext: 코루틴 안에서 해당 부분만 다른 코루틴으로 실행할 수 있게 해준다
            withContext(Dispatchers.Main){

            }
        }
    }
}