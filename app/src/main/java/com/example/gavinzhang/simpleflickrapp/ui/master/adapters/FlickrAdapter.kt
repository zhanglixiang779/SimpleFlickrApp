package com.example.gavinzhang.simpleflickrapp.ui.master.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.gavinzhang.simpleflickrapp.R
import com.example.gavinzhang.simpleflickrapp.ui.master.data.models.Item
import com.squareup.picasso.Picasso

class FlickrAdapter(context: Context?,
                    private val items: List<Item>) : BaseAdapter() {

    companion object {
        const val IMAGE_SIZE = 500
    }

    private val inflater: LayoutInflater
            = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = inflater.inflate(R.layout.item_view, parent, false)
        val photo = view.findViewById<ImageView>(R.id.photo)
        val owner = view.findViewById<TextView>(R.id.owner)
        val title = view.findViewById<TextView>(R.id.title)

        val item = getItem(position)
        Picasso.get()
                .load(getUrl(item))
                .resize(IMAGE_SIZE, IMAGE_SIZE)
                .centerCrop()
                .into(photo);
        owner.text = item.owner
        title.text = item.title

        return view
    }


    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = items[position].id.toLong()

    override fun getCount() = items.size

    fun getUrl(item: Item) =
        item.run { "https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg" }

}