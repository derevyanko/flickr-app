package com.example.flickrproject.presentation.view.details.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.flickrproject.presentation.view.details.PhotoDetailsFragment

class PhotoDetailsPagerAdapter(
    fragmentManager: FragmentManager,
    private val photoIds: List<String>
): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = photoIds.size

    override fun getItem(position: Int) = PhotoDetailsFragment.getInstance(
        photoId = photoIds[position]
    )
}