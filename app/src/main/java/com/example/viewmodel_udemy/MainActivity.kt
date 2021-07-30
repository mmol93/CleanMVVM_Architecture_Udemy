package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel_udemy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binder : ActivityMainBinding
    private lateinit var viewModel : MainActivityViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        viewModelFactory = ViewModelFactory(125)
        // ViewModel 대상 설정
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
        // observe를 이용하여 관찰할 대상(변수) 설정
        viewModel.total.observe(this, Observer {
            binder.textView.text = it.toString()
        })


        binder.addButton.setOnClickListener {
            viewModel.add(binder.editText.text.toString().toInt())
        }
    }
}