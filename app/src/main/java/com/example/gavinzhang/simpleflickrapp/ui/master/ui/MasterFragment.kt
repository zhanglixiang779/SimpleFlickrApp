package com.example.gavinzhang.simpleflickrapp.ui.master.ui

import android.content.Context
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
import com.example.gavinzhang.simpleflickrapp.databinding.MasterFragmentBinding
import com.example.gavinzhang.simpleflickrapp.ui.master.adapters.FlickrAdapter
import com.example.gavinzhang.simpleflickrapp.ui.master.data.FlickrRepository
import com.example.gavinzhang.simpleflickrapp.ui.master.viewmodels.SharedViewModel
import com.felipecsl.quickreturn.library.QuickReturnAttacher
import com.felipecsl.quickreturn.library.widget.QuickReturnAdapter
import com.felipecsl.quickreturn.library.widget.QuickReturnTargetView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.master_fragment.view.*
import javax.inject.Inject

class MasterFragment : DaggerFragment() {

    @Inject
    lateinit var repository: FlickrRepository

    lateinit var listview: ListView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var imageTitle: TextView
    lateinit var quickReturn: TextView
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = MasterFragmentBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.setLifecycleOwner(this)
        viewModel = getViewModel()
        binding.viewModel = viewModel
        viewModel.titleLiveData.value = "Title"
        quickReturn = rootView.quick_return
        listview = rootView.findViewById(R.id.listview)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchPhotos()
        observeItems()
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
            listview.adapter = QuickReturnAdapter(adapter)
            val attacher = QuickReturnAttacher.forView(listview)
            attacher.addTargetView(quickReturn, QuickReturnTargetView.POSITION_TOP, dpToPx(activity!!, 50f))
            listview.setOnItemClickListener { _, view, position, _ ->
                val action = MasterFragmentDirections
                        .actionMasterFragmentToDetailFragment()
                action.run {
                    setUrl(adapter.getUrl(it[position - 1]))
                    setTitle(it[position - 1].title)
                }
                Navigation.findNavController(view).navigate(action)
            }
        })
    }

    private fun dpToPx(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}
