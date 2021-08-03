package com.example.viewmodel_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.viewmodel_udemy.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var binder : ActivityMainBinding
    private lateinit var mainActivityViewModel : MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
//        mainActivityViewModel.getUserData()
        mainActivityViewModel.users.observe(this, Observer {myUsers ->
            myUsers.forEach {
                Log.d("test", "name is : ${it.name}")
            }
        })
        setContentView(binder.root)
    }
}