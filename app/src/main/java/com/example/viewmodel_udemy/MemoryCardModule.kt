package com.example.viewmodel_udemy

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule(val memorySize:Int) {
    @Provides
    fun providesMemoryCard() : MemoryCard{
        Log.d("test", "Size of memory is $memorySize")
        return MemoryCard()
    }
}