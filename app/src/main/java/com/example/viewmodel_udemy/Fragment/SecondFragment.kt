package com.example.viewmodel_udemy.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.viewmodel_udemy.R
import com.example.viewmodel_udemy.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binder : FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        binder.button.setOnClickListener {
            // fragment끼리의 데이터 전달은 일반적으로 bundle을 사용한다
            val bundle = bundleOf("name" to binder.editText.text.toString())
            it.findNavController().navigate(R.id.action_secondFragment_to_emailFragment, bundle)
        }
        return binder.root
    }
}