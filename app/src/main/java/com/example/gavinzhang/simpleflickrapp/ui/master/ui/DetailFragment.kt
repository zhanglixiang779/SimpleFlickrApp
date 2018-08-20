package com.example.gavinzhang.simpleflickrapp.ui.master.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.gavinzhang.simpleflickrapp.databinding.DetailFragmentBinding
import com.example.gavinzhang.simpleflickrapp.ui.master.viewmodels.SharedViewModel
import dagger.android.support.DaggerFragment

class DetailFragment : DaggerFragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding= DetailFragmentBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val url = DetailFragmentArgs.fromBundle(arguments).url
        binding.url = url

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SharedViewModel (null) as T
            }

        }).get(SharedViewModel::class.java)
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.titleLiveData.value = DetailFragmentArgs.fromBundle(arguments).title
    }
}
