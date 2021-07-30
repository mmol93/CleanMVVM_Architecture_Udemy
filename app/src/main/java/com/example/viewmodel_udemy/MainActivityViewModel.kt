package com.example.viewmodel_udemy

import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal : Int) : ViewModel() {
    private var total = 0

    init {
        total = startingTotal
    }

    fun getTotal() : Int{
        return total
    }

    fun add(input : Int){
        total += input
    }
}