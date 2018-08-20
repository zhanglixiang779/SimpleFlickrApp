package com.example.gavinzhang.simpleflickrapp.ui.master.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.gavinzhang.simpleflickrapp.databinding.ItemViewBinding
import com.example.gavinzhang.simpleflickrapp.ui.master.data.models.Item

class FlickrAdapter(context: Context?,
                    private val items: List<Item>) : BaseAdapter() {

    companion object {
        const val IMAGE_SIZE = 500
    }

    private val inflater: LayoutInflater
            = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val binding = ItemViewBinding.inflate(inflater, parent, false)
        val rootView = binding.root
        binding.item = getItem(position)
        binding.adapter = this

        return rootView
    }


    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = items[position].id.toLong()

    override fun getCount() = items.size

    fun getUrl(item: Item) =
        item.run { "https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg" }

}