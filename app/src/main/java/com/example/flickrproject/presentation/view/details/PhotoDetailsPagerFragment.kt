package com.example.flickrproject.presentation.view.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.base.State
import com.example.flickrproject.R
import com.example.flickrproject.databinding.FragmentPhotoDetailsPagerBinding
import com.example.flickrproject.presentation.view.details.adapter.PhotoDetailsPagerAdapter
import com.example.flickrproject.presentation.viewmodel.RecentPhotosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoDetailsPagerFragment: Fragment(R.layout.fragment_photo_details_pager) {

    private lateinit var binding: FragmentPhotoDetailsPagerBinding

    private lateinit var photoDetailsPagerAdapter: PhotoDetailsPagerAdapter

    private val photosViewModel: RecentPhotosViewModel by activityViewModels()

    private val photoIds: List<String> by lazy {
        val state = photosViewModel.recentPhotos.value ?: return@lazy emptyList()
        when (state) {
            is State.Success -> state.data.photos.map  { it.id }
            else -> emptyList()
        }
    }

    private val initialPosition: Int
        get() = arguments?.getInt(KEY_PHOTO_POSITION) ?: 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotoDetailsPagerBinding.bind(view)

        setupView()

        setCurrentPhoto()
    }

    private fun setupView() {
        setupPhotoDetailsViewPager()
    }

    private fun setCurrentPhoto() {
        binding.viewPagerPhotoDetails.currentItem = initialPosition
    }

    private fun setupPhotoDetailsViewPager() = with (binding) {
        val adapter = PhotoDetailsPagerAdapter(
            fragmentManager = childFragmentManager,
            photoIds = photoIds
        )
        photoDetailsPagerAdapter = adapter

        viewPagerPhotoDetails.adapter = photoDetailsPagerAdapter
        viewPagerPhotoDetails.currentItem = initialPosition
    }

    companion object {

        const val KEY_PHOTO_POSITION = "PHOTO_POSITION"

        fun getInstance(position: Int) = PhotoDetailsPagerFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_PHOTO_POSITION, position)
            }
        }
    }
}