package com.example.gavinzhang.simpleflickrapp.ui.master.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gavinzhang.simpleflickrapp.R
import com.example.gavinzhang.simpleflickrapp.ui.master.adapters.FlickrAdapter
import com.example.gavinzhang.simpleflickrapp.ui.master.data.FlickrRepository
import com.example.gavinzhang.simpleflickrapp.ui.master.utils.NetworkStatus
import com.example.gavinzhang.simpleflickrapp.ui.master.viewmodels.SharedViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MasterFragment : DaggerFragment() {

    @Inject
    lateinit var repository: FlickrRepository

    lateinit var listview: ListView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var imageTitle: TextView
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.master_fragment, container, false)
        imageTitle = view.findViewById(R.id.imageTitle)
        listview = view.findViewById(R.id.listview)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchPhotos()
        }
        listview.setOnItemClickListener { _, _, position, l ->

        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        viewModel.fetchPhotos()
        observeItems()
        observeNetworkStatus()
        observeTitle()
    }

    private fun getViewModel(): SharedViewModel {
        return ViewModelProviders.of(activity!!, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SharedViewModel (repository) as T
            }

        }).get(SharedViewModel::class.java)
    }

    private fun observeItems() {
        viewModel.itemsLiveData?.observe(this, Observer {
            val adapter = FlickrAdapter(context, it)
            listview.adapter = adapter
            listview.setOnItemClickListener { _, view, position, l ->
                val action = MasterFragmentDirections
                        .actionMasterFragmentToDetailFragment()
                action.run {
                    setUrl(adapter.getUrl(it[position]))
                    setTitle(it[position].title)
                }
                Navigation.findNavController(view).navigate(action)
            }
        })
    }

    private fun observeNetworkStatus() {
        viewModel.networkStatusLiveData?.observe(this, Observer {
            when(it) {
                NetworkStatus.LOADING -> swipeRefreshLayout.isRefreshing = true
                NetworkStatus.LOADED -> swipeRefreshLayout.isRefreshing = false
                else -> {
                    //error handling
                }
            }
        })
    }

    private fun observeTitle() {
        viewModel.titleLiveData.observe(this, Observer {
            imageTitle.text = it
        })
    }
}
