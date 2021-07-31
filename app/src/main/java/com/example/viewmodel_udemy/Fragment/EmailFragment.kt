package com.example.viewmodel_udemy.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.viewmodel_udemy.R
import com.example.viewmodel_udemy.databinding.FragmentEmailBinding

class EmailFragment : Fragment() {
    private lateinit var binder : FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)
        val nameData = arguments?.getString("name").toString()
        Log.d("test", "nameData: $nameData")
        // 다음 프래그먼트로 2개 이상의 데이터를 보내야할 경우 2가지 방법이 있다
        // 1. 현재 활성화된 방법: arrayOf를 활용하여 배열로 보내는 방법
        val dataList = arrayOf(nameData, "d")

        binder.button.setOnClickListener {
            dataList[1] = (binder.editText.text.toString())
            // 2. 번들 자체에 2개의 데이터를 넣는 방법
            // ,(쉼표)를 이용하여 번들 1개에 여러 개의 변수를 넣을 수 있다
            // val bundle = bundleOf("name" to nameData, "email" to binder.editText.text.toString())
            val bundle = bundleOf("email" to dataList)
            it.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment, bundle)
        }

        return binder.root
    }
}