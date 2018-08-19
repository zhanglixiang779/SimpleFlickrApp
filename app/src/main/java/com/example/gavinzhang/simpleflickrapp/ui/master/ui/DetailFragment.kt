package com.example.gavinzhang.simpleflickrapp.ui.master.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.gavinzhang.simpleflickrapp.R
import com.example.gavinzhang.simpleflickrapp.ui.master.viewmodels.SharedViewModel
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment

class DetailFragment : DaggerFragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.detail_fragment, container, false)
        val picture = view.findViewById<ImageView>(R.id.picture)
        val url = DetailFragmentArgs.fromBundle(arguments).url
        Picasso.get().load(url).into(picture)

        return view
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
