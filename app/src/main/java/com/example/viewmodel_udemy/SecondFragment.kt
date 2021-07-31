package com.example.viewmodel_udemy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.viewmodel_udemy.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binder : FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)

        return binder.root
    }

}