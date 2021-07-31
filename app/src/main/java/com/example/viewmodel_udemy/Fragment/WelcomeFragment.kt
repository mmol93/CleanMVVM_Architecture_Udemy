package com.example.viewmodel_udemy.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.viewmodel_udemy.R
import com.example.viewmodel_udemy.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binder : FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        val data = arguments?.getStringArray("email")
        binder.nameTextView.text = data!![0]
        binder.emailTextView.text = data!![1]

        return binder.root
    }
}