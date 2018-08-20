package com.example.gavinzhang.simpleflickrapp.ui.master.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gavinzhang.simpleflickrapp.R
import com.example.gavinzhang.simpleflickrapp.ui.master.utils.NetworkStatus
import com.squareup.picasso.Picasso

class BindingAdapters {

    companion object {
        @BindingAdapter("isRefreshing")
        @JvmStatic
        fun isRefreshing(swipeRefreshLayout: SwipeRefreshLayout, networkStatus: NetworkStatus) {
            when(networkStatus) {
                NetworkStatus.LOADED-> swipeRefreshLayout.isRefreshing = false
                NetworkStatus.LOADING -> swipeRefreshLayout.isRefreshing = true
                NetworkStatus.FAILED -> swipeRefreshLayout.isRefreshing = false
            }
        }

        @BindingAdapter("android:src")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            when(imageView.id) {
                R.id.photo -> Picasso.get()
                        .load(url)
                        .resize(FlickrAdapter.IMAGE_SIZE, FlickrAdapter.IMAGE_SIZE)
                        .centerCrop()
                        .into(imageView)
                R.id.picture -> Picasso.get().load(url).into(imageView)
            }
        }
    }
}