package com.example.gavinzhang.simpleflickrapp.ui.master.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gavinzhang.simpleflickrapp.ui.master.data.FlickrRepository
import com.example.gavinzhang.simpleflickrapp.ui.master.utils.NetworkStatus

class SharedViewModel(private val repository: FlickrRepository?) : ViewModel() {

    var itemsLiveData = repository?.itemsLiveData
    var networkStatusLiveData = repository?.networkStatusLiveData
    val titleLiveData = MutableLiveData<String>()

    fun fetchPhotos() {
        networkStatusLiveData?.value = NetworkStatus.LOADING
        repository?.fetchPhotos()
    }
}
