package com.example.gavinzhang.simpleflickrapp.ui.master.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.gavinzhang.simpleflickrapp.ui.master.data.models.FlickrResponse
import com.example.gavinzhang.simpleflickrapp.ui.master.utils.NetworkStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FlickrRepository @Inject constructor( val apiService: ApiService) {

    val flickrResponseLiveData = MutableLiveData<FlickrResponse>()
    val itemsLiveData = Transformations.map(flickrResponseLiveData) { it.photos.photo }
    val networkStatusLiveData = MutableLiveData<NetworkStatus>()

    companion object {
        const val API_KEY = "fee10de350d1f31d5fec0eaf330d2dba"
        const val METHOD = "flickr.photos.getRecent"
        const val FORMAT = "json"
    }

    fun fetchPhotos() {
        val call = apiService.fetchPhotos(method = METHOD,
                                                            apiKey = API_KEY,
                                                            format = FORMAT,
                                                            callback = true)
        call.enqueue(object : Callback<FlickrResponse> {
            override fun onFailure(call: Call<FlickrResponse>?, t: Throwable?) {
                //error handling
                networkStatusLiveData.value = NetworkStatus.FAILED
            }

            override fun onResponse(call: Call<FlickrResponse>?, response: Response<FlickrResponse>?) {
                flickrResponseLiveData.value = response?.body()
                networkStatusLiveData.value = NetworkStatus.LOADED
            }
        })
    }
}