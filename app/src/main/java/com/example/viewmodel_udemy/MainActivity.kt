package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel_udemy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binder : ActivityMainBinding
    private lateinit var viewModel : MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View들을 DataBinding으로 묶어준다(평소 쓰던 일반적인 ViewBinding과 다름)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // ViewModel을 추가한다
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        // 레이아웃에 있는 data가 어느 viewModel을 대상으로할지 지정해준다
        // 이렇게 하면 LiveData와 DataBinding을 연결할 수 있게 된다
        binder.mainViewModel = viewModel
        // 현재 레이아웃의 context를 observe 한다
        binder.lifecycleOwner = this

        binder.button.setOnClickListener {
            val data = binder.editText.text.toString().toInt()
            viewModel.number.value = (viewModel.number.value)!!.plus(data)
            Log.d("test", "result: ${viewModel.number.value}")
        }
    }
}