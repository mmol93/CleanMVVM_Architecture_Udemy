package com.example.viewmodel_udemy

import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun providesDataSource():DataSource{
        return DataSource()
    }
}