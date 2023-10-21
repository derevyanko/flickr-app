package com.example.flickrproject.presentation.view.recent

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.base.ErrorEntity
import com.example.domain.base.State
import com.example.flickrproject.R
import com.example.flickrproject.databinding.FragmentRecentPhotosBinding
import com.example.domain.models.recent.RecentPhotos
import com.example.flickrproject.presentation.view.details.PhotoDetailsPagerFragment
import com.example.flickrproject.presentation.view.recent.adapter.PhotosAdapter
import com.example.flickrproject.presentation.viewmodel.RecentPhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentPhotosFragment: Fragment(R.layout.fragment_recent_photos), PhotosAdapter.PhotoListener {

    private lateinit var binding: FragmentRecentPhotosBinding

    private val photosViewModel: RecentPhotosViewModel by activityViewModels()

    private val photosAdapter = PhotosAdapter(listener = this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecentPhotosBinding.bind(view)

        setupView()
        setupObservers()

        fetchRecentPhotos()
    }

    private fun setupView() = with (binding) {
        swipeRefreshLayout.setOnRefreshListener { fetchRecentPhotos() }

        setupRecyclerView()
    }

    private fun setupRecyclerView() = with (binding) {
        photosRecyclerView.layoutManager = GridLayoutManager(context, 3)
        photosRecyclerView.adapter = photosAdapter
    }

    private fun setupObservers() {
        observePhotos()
    }

    private fun observePhotos() {
        photosViewModel.recentPhotos.observe(viewLifecycleOwner) { state ->
            updateRecentPhotos(state)
        }
    }

    private fun updateRecentPhotos(state: State<RecentPhotos>) = when (state) {
        is State.Success -> handleSuccessState(recentPhotos = state.data)
        is State.Failure -> handleFailureState(state)
        is State.Loading -> handleLoadingState(isRefreshing = true)
        else -> {}
    }

    private fun handleSuccessState(recentPhotos: RecentPhotos) = with (binding) {
        handleLoadingState(isRefreshing = false)

        photosRecyclerView.isVisible = true
        photosAdapter.updateDataSet(recentPhotos.photos)
    }

    private fun handleFailureState(state: State.Failure<RecentPhotos>) = with (binding) {
        handleLoadingState(isRefreshing = false)

        when (state.error) {
            is ErrorEntity.Network -> {
                photosRecyclerView.isVisible = false
                layoutError.root.isVisible = true
                layoutError.buttonError.setOnClickListener { fetchRecentPhotos() }
            }
            else -> {
                photosRecyclerView.isVisible = false
                layoutError.root.isVisible = true
                layoutError.buttonError.setOnClickListener { fetchRecentPhotos() }
            }
        }
    }

    private fun handleLoadingState(isRefreshing: Boolean) = with (binding) {
        swipeRefreshLayout.isRefreshing = isRefreshing
        layoutLoading.root.isVisible = isRefreshing

        photosRecyclerView.isVisible = false
        layoutError.root.isVisible = false
    }

    private fun fetchRecentPhotos() {
        photosViewModel.getPhotosList()
    }

    override fun onItemClick(position: Int) {
        val photoDetailsPagerFragment = PhotoDetailsPagerFragment.getInstance(position)

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerViewMain, photoDetailsPagerFragment)
            .addToBackStack(photoDetailsPagerFragment::class.qualifiedName)
            .commit()
    }
}