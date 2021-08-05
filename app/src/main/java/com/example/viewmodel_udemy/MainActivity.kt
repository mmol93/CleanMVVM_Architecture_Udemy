package com.example.viewmodel_udemy

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.database.SubscribeRepository
import com.example.database.SubscriberDatabase
import com.example.viewmodel_udemy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binder : ActivityMainBinding
    private lateinit var subscribeViewModel: SubscribeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscribeRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscribeViewModel = ViewModelProvider(this, factory).get(SubscribeViewModel::class.java)
        binder.myViewModel = subscribeViewModel
        binder.lifecycleOwner = this
        displaySubscribersList()
    }
    private fun displaySubscribersList(){
        // getSaveSubscribers를 observe한다
        subscribeViewModel.getSaveSubscribers().observe(this, Observer {
            Log.d("test", it.toString())
        })
    }
}