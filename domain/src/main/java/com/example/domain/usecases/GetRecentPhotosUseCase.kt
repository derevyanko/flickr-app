package com.example.domain.usecases

import com.example.domain.repository.FlickrRepository

class GetRecentPhotosUseCase(
    private val repository: FlickrRepository
) {

    suspend operator fun invoke(itemsPerPage: Int) = repository.getRecentPhotos(itemsPerPage)
}