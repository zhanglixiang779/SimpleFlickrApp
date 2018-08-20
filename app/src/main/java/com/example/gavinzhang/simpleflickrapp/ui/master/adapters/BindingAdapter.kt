package com.example.gavinzhang.simpleflickrapp.ui.master.adapters

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gavinzhang.simpleflickrapp.ui.master.utils.NetworkStatus

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
    }
}