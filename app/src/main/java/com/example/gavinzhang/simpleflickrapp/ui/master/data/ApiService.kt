package com.example.gavinzhang.simpleflickrapp.ui.master.data

import com.example.gavinzhang.simpleflickrapp.ui.master.data.models.FlickrResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("services/rest/")
    fun fetchPhotos(@Query("method") method: String,
                      @Query("api_key") apiKey: String,
                      @Query("format") format: String,
                      @Query("nojsoncallback") callback: Boolean):
            Call<FlickrResponse>
}