package com.example.viewmodel_udemy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.viewmodel_udemy.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binder : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binder.button.setOnClickListener {
            // action에 대한 id는 nav_graph에서 fragment를 서로 연결하면 생긴다
            // findNavController로 navigation 메서드를 이용하여 해당 id의 action을 실행
            it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment)
        }
        return binder.root
    }
}