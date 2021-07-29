package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel_udemy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binder : ActivityMainBinding
    private lateinit var viewModel : MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        // ViewModel 대상 설정
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binder.textView.text = viewModel.getCurrentCount().toString()
        binder.button.setOnClickListener {
            binder.textView.text = viewModel.getUpdatedCount().toString()
        }
    }
}