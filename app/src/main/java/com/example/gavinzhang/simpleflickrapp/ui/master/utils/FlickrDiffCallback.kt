package com.example.gavinzhang.simpleflickrapp.ui.master.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.gavinzhang.simpleflickrapp.ui.master.data.models.Item

class FlickrDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem == newItem
}