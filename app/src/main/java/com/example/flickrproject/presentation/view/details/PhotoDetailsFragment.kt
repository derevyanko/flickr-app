package com.example.flickrproject.presentation.view.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.domain.base.ErrorEntity
import com.example.domain.base.State
import com.example.domain.models.details.Tag
import com.example.flickrproject.R
import com.example.flickrproject.databinding.FragmentPhotoDetailsBinding
import com.example.flickrproject.di.app.GlideApp
import com.example.domain.models.details.PhotoDetails
import com.example.flickrproject.presentation.viewmodel.PhotoDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import java.text.SimpleDateFormat

@AndroidEntryPoint
class PhotoDetailsFragment: Fragment(R.layout.fragment_photo_details) {

    private lateinit var binding: FragmentPhotoDetailsBinding

    private val photoDetailsViewModel by viewModels<PhotoDetailsViewModel>()

    private val photoId: String
        get() = arguments?.getString(KEY_PHOTO_ID) ?: ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotoDetailsBinding.bind(view)

        setupObservers()

        fetchPhotoDetails()
    }

    private fun setupObservers() {
        addPhotoDetailsObserver()
    }

    private fun fetchPhotoDetails() {
        photoDetailsViewModel.getPhotoDetails(photoId)
    }

    private fun addPhotoDetailsObserver() {
        photoDetailsViewModel.photoDetails.observe(viewLifecycleOwner) { state ->
            updatePhotoDetails(state)
        }
    }

    private fun updatePhotoDetails(state: State<PhotoDetails>) = when (state) {
        is State.Success -> handleSuccessState(photoDetails = state.data)
        is State.Failure -> handleFailureState(state)
        is State.Loading -> handleLoadingState(true)
        else -> {}
    }

    private fun handleSuccessState(photoDetails: PhotoDetails) {
        handleLoadingState(false)

        binding.layoutPhotoDetails.root.isVisible = true
        bindPhotoDetails(photoDetails)
    }

    private fun handleFailureState(state: State.Failure<PhotoDetails>) = with (binding) {
        handleLoadingState(false)

        when (state.error) {
            is ErrorEntity.Network -> {
                layoutPhotoDetails.root.isVisible = false
                layoutError.root.isVisible = true
                layoutError.buttonError.setOnClickListener { fetchPhotoDetails() }
            }
            else -> {
                layoutPhotoDetails.root.isVisible = false
                layoutError.root.isVisible = true
                layoutError.buttonError.setOnClickListener { fetchPhotoDetails() }
            }
        }
    }

    private fun handleLoadingState(isRefreshing: Boolean) = with (binding) {
        layoutLoading.root.isVisible = isRefreshing

        layoutPhotoDetails.root.isVisible = false
        layoutError.root.isVisible = false
    }

    private fun bindPhotoDetails(photoDetails: PhotoDetails) = with (binding.layoutPhotoDetails) {
        bindImage(photoDetails.url)
        bindInfo(photoDetails)
    }

    private fun bindImage(url: String) = with (binding.layoutPhotoDetails) {
        context?.let {
            GlideApp.with(it)
                .load(url)
                .into(includeImage.imageView)
        }
    }

    private fun bindInfo(photoDetails: PhotoDetails) = with (binding.layoutPhotoDetails) {
        bindTitle(photoDetails.title)
        bindDescription(photoDetails.description)
        bindTags(photoDetails.tags)
        bindOwner(photoDetails.ownerUserName)
        bindDatePosted(photoDetails.datePosted)
    }

    private fun bindTitle(title: String) = with (binding.layoutPhotoDetails) {
        includeTitle.textView.text = title
        includeTitle.root.isVisible = title.isNotBlank()
    }

    private fun bindDescription(description: String) = with (binding.layoutPhotoDetails) {
        includeDescription.textView.text = description
        includeDescription.root.isVisible = description.isNotBlank()
    }

    private fun bindTags(tags: List<Tag>) = with (binding.layoutPhotoDetails) {
        val tagsString = tags.joinToString(", ") { it.content }
        includeTags.textView.text = tagsString
        includeTags.root.isVisible = tags.isNotEmpty()
    }

    private fun bindOwner(ownerUserName: String) = with (binding.layoutPhotoDetails) {
        includeOwner.ownerUserNameTextView.text = ownerUserName
        includeOwner.root.isVisible = ownerUserName.isNotEmpty()
    }

    private fun bindDatePosted(datePosted: String) = with (binding.layoutPhotoDetails) {
        val dateTime = SimpleDateFormat("MM/dd/yyyy").run {
            val date = Date(datePosted.toLong() * 1000)
            return@run this.format(date)
        }

        includeDatePosted.textView.text = dateTime
        includeDatePosted.root.isVisible = dateTime.isNotBlank()
    }

    companion object {

        const val KEY_PHOTO_ID = "PHOTO_ID"

        fun getInstance(
            photoId: String
        ) = PhotoDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_PHOTO_ID, photoId)
            }
        }
    }
}