package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.database.Subscriber
import com.example.viewmodel_udemy.R
import com.example.viewmodel_udemy.databinding.ListItemBinding

class MainRecyclerAdapter(private val subscribeList: List<Subscriber>, private val clickListener:(Subscriber)->Unit) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        // 데이터 바인딩을 하여 리턴한다
        val binding : ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 각 subscribe 정보를 받은 람다식과 연결시켜준다
        holder.bind(subscribeList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return subscribeList.size
    }
}

class ViewHolder(val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root){
    // 데이터베이스에 있는 요소를 사용하기 위해 subscriber를 받는다
    fun bind(subscriber:Subscriber, clickListener:(Subscriber)->Unit){
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItemLayout.setOnClickListener {
            clickListener(subscriber)
        }
    }
}