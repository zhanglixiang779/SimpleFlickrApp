package com.example.gavinzhang.simpleflickrapp.ui.master.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gavinzhang.simpleflickrapp.R
import com.example.gavinzhang.simpleflickrapp.ui.master.adapters.FlickrAdapter
import com.example.gavinzhang.simpleflickrapp.ui.master.data.models.Item
import com.squareup.picasso.Picasso

class FlickrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var photo: ImageView
    lateinit var owner: TextView
    lateinit var title: TextView

    fun onbind(item: Item, callback: (String, String, View) -> Unit ) {
        val url = item.run { "https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg" }
        itemView.setOnClickListener {
            callback(url, item.title, itemView)
        }
        itemView.run {
            photo = findViewById(R.id.photo)
            owner = findViewById(R.id.owner)
            title = findViewById(R.id.title)
        }

        Picasso.get()
                .load(url)
                .resize(FlickrAdapter.IMAGE_SIZE, FlickrAdapter.IMAGE_SIZE)
                .centerCrop()
                .into(photo);
        owner.text = item.owner
        title.text = item.title
    }
}