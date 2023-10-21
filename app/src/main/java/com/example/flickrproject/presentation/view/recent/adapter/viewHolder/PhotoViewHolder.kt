package com.example.flickrproject.presentation.view.recent.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.flickrproject.databinding.ItemPhotoBinding
import com.example.domain.models.recent.Photo
import com.example.flickrproject.R
import com.example.flickrproject.di.app.GlideApp
import com.example.flickrproject.presentation.view.recent.adapter.PhotosAdapter

class PhotoViewHolder(
    itemView: View,
    private val listener: PhotosAdapter.PhotoListener
): RecyclerView.ViewHolder(itemView) {

    private val binding = ItemPhotoBinding.bind(itemView)

    fun bind(item: Photo, position: Int) = with(binding) {
        bindUrl(item.url)
        bindCaption(item.title)

        root.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    private fun bindUrl(url: String) = with (binding) {
        GlideApp.with(itemView.context)
            .load(url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .centerCrop()
            .into(imageView)
    }

    private fun bindCaption(title: String) = with (binding) {
        imageCaption.text = title
    }
}