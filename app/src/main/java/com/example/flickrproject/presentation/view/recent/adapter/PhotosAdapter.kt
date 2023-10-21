package com.example.flickrproject.presentation.view.recent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrproject.R
import com.example.domain.models.recent.Photo
import com.example.flickrproject.presentation.view.recent.adapter.viewHolder.PhotoViewHolder

class PhotosAdapter(
    private var items: List<Photo> = emptyList(),
    private val listener: PhotoListener
): RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(
            itemView = inflater,
            listener = listener
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(
            item = items[position],
            position = position
        )
    }

    override fun getItemCount() = items.size

    fun updateDataSet(items: List<Photo>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface PhotoListener {

        fun onItemClick(position: Int)
    }
}