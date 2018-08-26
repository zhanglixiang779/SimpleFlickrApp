package com.example.gavinzhang.simpleflickrapp.ui.master.di

import com.example.gavinzhang.simpleflickrapp.ui.master.data.FlickrRepository
import com.example.gavinzhang.simpleflickrapp.ui.master.viewmodels.SharedViewModel
import dagger.Module
import dagger.Provides

@Module
class MasterActivityModule {
    @Provides
    fun provideSharedViewModel(repository: FlickrRepository) =
            SharedViewModel(repository)
}