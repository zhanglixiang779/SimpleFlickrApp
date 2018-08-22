package com.example.gavinzhang.simpleflickrapp.ui.master.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.gavinzhang.simpleflickrapp.R
import com.example.gavinzhang.simpleflickrapp.ui.master.data.models.Item
import com.example.gavinzhang.simpleflickrapp.ui.master.ui.viewholders.FlickrViewHolder
import com.example.gavinzhang.simpleflickrapp.ui.master.utils.FlickrDiffCallback

class FlickrAdapter( private val callback: (String, String, View) -> Unit) : ListAdapter<Item, FlickrViewHolder>(FlickrDiffCallback()) {

    companion object {
        const val IMAGE_SIZE = 500
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_view, parent, false)
        return FlickrViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FlickrViewHolder, position: Int) {
        holder.onbind(getItem(position), callback)
    }
}